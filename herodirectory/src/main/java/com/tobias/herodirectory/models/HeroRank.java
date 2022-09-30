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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="hero_ranks")
public class HeroRank {
	//getters and setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="rank name is required!")
    @Size(min=1, max=10, message="rank name must be 1-10 characters")
    private String name;
    
    //for sorting
    @NotNull
    @Positive
    private Integer relativePower;
    
    //for showing rank as a color: #FFFFFF
    @NotEmpty(message="color is required!")
    @Size(min=7, max=7, message="color must be 7 characters: #FFFFFF")
    private String color;
    
	//one hero rank has many heroes but one hero has one hero rank
    @OneToMany(mappedBy="heroRank", fetch = FetchType.LAZY)
	private List<HeroUser> heroes;
    
    //constructor and special
    public HeroRank() {}

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

	public Integer getRelativePower() {
		return relativePower;
	}

	public void setRelativePower(Integer relativePower) {
		this.relativePower = relativePower;
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
