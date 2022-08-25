package com.tobias.herodirectory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tobias.herodirectory.models.Gender;
import com.tobias.herodirectory.repositories.GenderRepo;

@Service
public class GenderService {

	@Autowired
	private GenderRepo genderRepo;
	
	//read all
	public List<Gender> readAll(){
		return genderRepo.findAll();
	}
	
	//create after extra validation
	public Gender create(Gender gender, BindingResult result) {
		//validation: name does not already exist
		Optional<Gender> maybeGender = genderRepo.findByName(gender.getName());
		if(maybeGender.isPresent())
			result.rejectValue("name", "taken", "that gender name already exists");

		if(result.hasErrors())
			return null;
		
		return genderRepo.save(gender);
	}
}
