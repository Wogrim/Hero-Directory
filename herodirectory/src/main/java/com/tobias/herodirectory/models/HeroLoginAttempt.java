package com.tobias.herodirectory.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//NOT IN DATABASE
public class HeroLoginAttempt {
	//getters and setters

    @NotEmpty(message="Hero License Number required")
    @Size(min=10, max=10, message="Hero License Number is 10 digits")
    private String heroLicenseNumber;
	
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be 8+ characters")
    private String password;
	
	//constructor and special
	public HeroLoginAttempt() {}

	//getters and setters

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeroLicenseNumber() {
		return heroLicenseNumber;
	}

	public void setHeroLicenseNumber(String heroLicenseNumber) {
		this.heroLicenseNumber = heroLicenseNumber;
	}
	
	
}
