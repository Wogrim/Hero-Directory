package com.tobias.herodirectory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tobias.herodirectory.models.HeroRank;
import com.tobias.herodirectory.repositories.HeroRankRepo;

@Service
public class HeroRankService {

	@Autowired HeroRankRepo heroRankRepo;
	
	//read all
	public List<HeroRank> readAll(){
		return heroRankRepo.findAll();
	}
	
	//create after extra validation
	public HeroRank create(HeroRank heroRank, BindingResult result) {
		//validation: name does not already exist
		Optional<HeroRank> maybeHeroRank = heroRankRepo.findByName(heroRank.getName());
		if(maybeHeroRank.isPresent())
			result.rejectValue("name", "taken", "that rank name already exists");

		//validation: relativePower does not already exist (for sorting)
		maybeHeroRank = heroRankRepo.findByRelativePower(heroRank.getRelativePower());
		if(maybeHeroRank.isPresent())
			result.rejectValue("relativePower", "taken", "the power level must be different from othe ranks for sorting");
		
		if(result.hasErrors())
			return null;
		
		return heroRankRepo.save(heroRank);
	}
}
