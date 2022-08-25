package com.tobias.herodirectory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.AdminUser;

@Repository
public interface AdminUserRepo extends CrudRepository<AdminUser, Long> {
	@Override
	List<AdminUser> findAll();
	
	Optional<AdminUser> findByAdminGUID(String guid);
}
