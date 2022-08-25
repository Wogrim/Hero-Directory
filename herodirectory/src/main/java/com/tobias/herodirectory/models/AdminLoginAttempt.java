package com.tobias.herodirectory.models;

import javax.validation.constraints.NotEmpty;

//NOT IN DATABASE
public class AdminLoginAttempt {
	//getters and setters

	@NotEmpty(message="GUID is required")
	private String adminGUID;
	
    @NotEmpty(message="Password is required!")
    private String password;
	
	//constructor and special
	public AdminLoginAttempt() {}

	//getters and setters
	public String getAdminGUID() {
		return adminGUID;
	}

	public void setAdminGUID(String adminGUID) {
		this.adminGUID = adminGUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
