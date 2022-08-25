package com.tobias.herodirectory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tobias.herodirectory.models.HeroSpecialty;
import com.tobias.herodirectory.repositories.HeroSpecialtyRepo;

@Service
public class HeroSpecialtyService {

	@Autowired
	private HeroSpecialtyRepo heroSpecialtyRepo;
	
	//read all
	public List<HeroSpecialty> readAll(){
		return heroSpecialtyRepo.findAll();
	}
	
	//create after extra validation
	public HeroSpecialty create(HeroSpecialty heroSpecialty, BindingResult result) {
		//validation: name does not already exist
		Optional<HeroSpecialty> maybeSpecialty = heroSpecialtyRepo.findByName(heroSpecialty.getName());
		if(maybeSpecialty.isPresent())
			result.rejectValue("name", "taken", "that specialty name already exists");

		if(result.hasErrors())
			return null;
		
		return heroSpecialtyRepo.save(heroSpecialty);
	}
}
