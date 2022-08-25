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
@Table(name = "hero_specialties")
public class HeroSpecialty {
	//getters and setters

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "specialty name is required!")
	@Size(min = 1, max = 20, message = "specialty name must be 1-20 characters")
	private String name;

	// for showing specialty as a color: #FFFFFF
	@NotEmpty(message = "color is required!")
	@Size(min = 7, max = 7, message = "color must be 7 characters: #FFFFFF")
	private String color;

	// one hero specialty has many heroes, but one hero has one specialty
	@OneToMany(mappedBy = "heroSpecialty", fetch = FetchType.LAZY)
	private List<HeroUser> heroes;

	// constructor and special
	public HeroSpecialty() {}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<HeroUser> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<HeroUser> heroes) {
		this.heroes = heroes;
	}

}
