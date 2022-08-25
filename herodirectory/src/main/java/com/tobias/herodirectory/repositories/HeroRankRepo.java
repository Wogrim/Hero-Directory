package com.tobias.herodirectory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.HeroRank;

@Repository
public interface HeroRankRepo extends CrudRepository<HeroRank, Long> {
	@Override
	List<HeroRank> findAll();

	Optional<HeroRank> findByName(String name);
	
	Optional<HeroRank> findByRelativePower(Integer power);
}
