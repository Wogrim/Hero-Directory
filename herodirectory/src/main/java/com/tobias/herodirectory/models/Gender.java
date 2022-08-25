package com.tobias.herodirectory.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="genders")
public class Gender {
	//getters and setters
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="gender name is required!")
    @Size(min=3, max=10, message="gender name must be 3-10 characters")
    private String name;
    
	//one gender has many heroes but one hero has one gender
    @OneToMany(mappedBy="gender", fetch = FetchType.LAZY)
	private List<HeroUser> heroes;
    
    //constructor and special
    public Gender() {}
    
    //getters and setters
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HeroUser> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<HeroUser> heroes) {
		this.heroes = heroes;
	}
    
    
}
