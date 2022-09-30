package com.tobias.herodirectory.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.tobias.herodirectory.models.HeroLoginAttempt;
import com.tobias.herodirectory.models.HeroUser;
import com.tobias.herodirectory.models.UploadedImage;
import com.tobias.herodirectory.repositories.HeroUserRepo;

@Service
public class HeroUserService {

	@Autowired
	private HeroUserRepo heroRepo;
	
	@Autowired
	private UploadedImageService uploadedImageServ;

	// create after extra validation
	public HeroUser create(HeroUser heroUser, BindingResult result, MultipartFile file) {
		// validation: license does not already exist
		Optional<HeroUser> maybeHero = heroRepo.findByHeroLicenseNumber(heroUser.getHeroLicenseNumber());
		if (maybeHero.isPresent())
			result.rejectValue("heroLicenseNumber", "taken", "that license already has an account");
		
		//validation: hero name not already in use
		maybeHero = heroRepo.findByHeroName(heroUser.getHeroName());
		if (maybeHero.isPresent())
			result.rejectValue("heroName", "taken", "that hero name is already in use");

		// validation: confirm matches password
		if (!heroUser.getPassword().equals(heroUser.getConfirm()))
			result.rejectValue("confirm", "matches", "passwords don't match");

		if (result.hasErrors())
			return null;
		
		// try to save the profile picture
		try {
			UploadedImage image = uploadedImageServ.store(file);
			heroUser.setProfileImage(image);
		}
		catch(IOException e){
			result.rejectValue("profileImage","save","failed to save uploaded pic");
			return null;
		}
		
		// hash password
		String hashedPassword = BCrypt.hashpw(heroUser.getPassword(), BCrypt.gensalt());
		heroUser.setPassword(hashedPassword);
		
		//save the hero
		return heroRepo.save(heroUser);
	}

	// login
	public HeroUser login(HeroLoginAttempt heroLogin, BindingResult result) {
		// validation: account with this GUID exists
		Optional<HeroUser> maybeHero = heroRepo.findByHeroLicenseNumber(heroLogin.getHeroLicenseNumber());
		if (!maybeHero.isPresent()) {
			result.rejectValue("password", "Login", "wrong license number and/or password");
			return null;
		}

		// validation: password is correct
		HeroUser hero = maybeHero.get();
		if (!BCrypt.checkpw(heroLogin.getPassword(), hero.getPassword()))
			result.rejectValue("password", "Login", "wrong license number and/or password");

		if (result.hasErrors())
			return null;

		// valid
		return hero;
	}
	
	//read all
	public List<HeroUser> readAll(){
		return heroRepo.findAll();
	}
	
	//read one
	public HeroUser getById(Long id) {
		Optional<HeroUser> maybeHero = heroRepo.findById(id);
		if(maybeHero.isPresent())
			return maybeHero.get();
		else
			return null;
	}
	
	//update without changing password
	public HeroUser update(HeroUser heroUser) {
		//assuming we're updating without hero typing in password,
		//we need to make sure the password is in the heroUser
		HeroUser original = this.getById(heroUser.getId());
		if(original==null)
			return null;
		
		heroUser.setPassword(original.getPassword());
		
		return heroRepo.save(heroUser);
	}
	
	//read 20 most recently created heroes
	public List<HeroUser> readRecent20(){
		return heroRepo.findFirst20ByOrderByCreatedAtDesc();
	}
	
	//read 20 most recently created heroes
		public List<HeroUser> readRecent5(){
			return heroRepo.findFirst5ByOrderByCreatedAtDesc();
		}
}
