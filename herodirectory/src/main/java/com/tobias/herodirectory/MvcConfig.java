package com.tobias.herodirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	public static final String uploadFolder = "uploaded_images";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadPath = Paths.get(uploadFolder);
		String uploadPathString = uploadPath.toFile().getAbsolutePath();
		registry.addResourceHandler("/"+uploadFolder+"/**").addResourceLocations("file:/"+uploadPathString+"/");
	}
}
