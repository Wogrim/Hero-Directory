package com.tobias.herodirectory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.HeroUser;

@Repository
public interface HeroUserRepo extends CrudRepository<HeroUser, Long> {
	@Override
	List<HeroUser> findAll();
	
	Optional<HeroUser> findByHeroLicenseNumber(String license);
}
