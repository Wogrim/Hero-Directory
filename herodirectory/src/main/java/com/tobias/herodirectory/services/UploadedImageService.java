package com.tobias.herodirectory.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tobias.herodirectory.MvcConfig;
import com.tobias.herodirectory.models.UploadedImage;
import com.tobias.herodirectory.repositories.UploadedImageRepo;

@Service
public class UploadedImageService {

	private final Path folder = Paths.get(MvcConfig.uploadFolder);
	
	private final Pattern extensionPattern = Pattern.compile("[.][a-zA-Z0-9]+$");
	
	@Autowired
	private UploadedImageRepo uploadedImageRepo;
	
	public UploadedImage store(MultipartFile file) throws IOException {
		if(!Files.exists(folder))
			Files.createDirectories(folder);

		if(file.isEmpty())
			throw new IOException("empty file");
		
		UploadedImage inProgress = uploadedImageRepo.save(new UploadedImage());
		
		Matcher matcher = extensionPattern.matcher(file.getOriginalFilename());
		if(!matcher.find())
			throw new IOException("couldn't find file extension");
		String extension = matcher.group();
		inProgress.setFilename(inProgress.getId().toString() + extension);
		inProgress.setFoldername("/"+MvcConfig.uploadFolder+"/");
		
		Path destinationPath = this.folder.resolve(inProgress.getFilename());
		
		try(InputStream inputStream = file.getInputStream()){
			Files.copy(inputStream,  destinationPath, StandardCopyOption.REPLACE_EXISTING);
		}
		
		return this.update(inProgress);
	}
	
	public Path getPath(String filename) {
		return folder.resolve(filename);
	}
	
	public Resource load(String filename) throws IOException {
		try {
			Path file = getPath(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() && resource.isReadable())
				return resource;
			else
				throw new IOException("couldn't read file");
		}
		catch(MalformedURLException e) {
			throw new IOException("couldn't read file, bad URL");
		}
	}
	
	public void delete(UploadedImage uploadedImage) {
		uploadedImageRepo.delete(uploadedImage);
	}
	
	public UploadedImage update(UploadedImage uploadedImage) {
		return uploadedImageRepo.save(uploadedImage);
	}
}
