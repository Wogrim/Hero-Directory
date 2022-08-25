package com.tobias.herodirectory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.HeroSpecialty;

@Repository
public interface HeroSpecialtyRepo extends CrudRepository<HeroSpecialty, Long> {
	@Override
	List<HeroSpecialty> findAll();
	
	Optional<HeroSpecialty> findByName(String name);
}
