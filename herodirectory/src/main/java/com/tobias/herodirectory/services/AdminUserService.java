package com.tobias.herodirectory.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tobias.herodirectory.models.AdminLoginAttempt;
import com.tobias.herodirectory.models.AdminUser;
import com.tobias.herodirectory.repositories.AdminUserRepo;

@Service
public class AdminUserService {
	//TODO: update and delete admins

	@Autowired
	private AdminUserRepo adminRepo;
	
	//create after extra validation
	public AdminUser create(AdminUser adminUser, BindingResult result) {
		//validation: adminGUID does not already exist
		Optional<AdminUser> maybeAdmin = adminRepo.findByAdminGUID(adminUser.getAdminGUID());
		if(maybeAdmin.isPresent())
			result.rejectValue("adminGUID", "taken", "that GUID is already in use");
		
		//validation: confirm matches password
		if(!adminUser.getPassword().equals(adminUser.getConfirm()))
			result.rejectValue("confirm", "matches", "passwords don't match");
		
		if(result.hasErrors())
			return null;
		
		//hash password
		String hashedPassword = BCrypt.hashpw(adminUser.getPassword(), BCrypt.gensalt());
		adminUser.setPassword(hashedPassword);
		
		return adminRepo.save(adminUser);
	}
	
	//login
	public AdminUser login(AdminLoginAttempt adminLogin, BindingResult result) {
		//validation: account with this GUID exists
		Optional<AdminUser> maybeAdmin = adminRepo.findByAdminGUID(adminLogin.getAdminGUID());
		if(!maybeAdmin.isPresent()) {
			result.rejectValue("password", "Login", "wrong GUID and/or password");
			return null;
		}
		
		//validation: password is correct
		AdminUser admin = maybeAdmin.get();
		if(!BCrypt.checkpw(adminLogin.getPassword(), admin.getPassword()))
			result.rejectValue("password", "Login", "wrong GUID and/or password");
		
		if(result.hasErrors())
			return null;
		
		//valid
		return admin;
	}
	
	//read all
	public List<AdminUser> readAll(){
		return adminRepo.findAll();
	}
	
	//read one
	public AdminUser getById(Long id) {
		Optional<AdminUser> maybeAdmin = adminRepo.findById(id);
		if(maybeAdmin.isPresent())
			return maybeAdmin.get();
		else
			return null;
	}
}
