package com.tobias.herodirectory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tobias.herodirectory.models.UploadedImage;

@Repository
public interface UploadedImageRepo extends CrudRepository<UploadedImage, Long> {
	@Override
	List<UploadedImage> findAll();
}
