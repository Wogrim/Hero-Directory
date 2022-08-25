package com.tobias.herodirectory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.Gender;

@Repository
public interface GenderRepo extends CrudRepository<Gender, Long> {
	@Override
	List<Gender> findAll();
	
	Optional<Gender> findByName(String name);
}
