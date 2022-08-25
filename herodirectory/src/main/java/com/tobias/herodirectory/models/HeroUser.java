package com.tobias.herodirectory.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="heroes")
public class HeroUser {
	//getters and setters
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Hero License Number required")
    @Size(min=10, max=10, message="Hero License Number is 10 digits")
    private String heroLicenseNumber;
    
    @NotEmpty(message="Hero Name is required!")
    @Size(min=3, max=20, message="Hero Name must be 3-20 characters")
    private String heroName;
    
    @Size(min=0, max=20, message="Hero Power can be up to 20 characters")
    private String heroPower;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be 8+ characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    private String confirm;
    
    @Size(min=0, max=100, message="philosophy can be up to 100 characters")
    private String philosophy;
    
    @Size(min=0, max=200, message="non-public status message can be up to 200 characters")
    private String statusMessage;
    
    @Size(min=0, max=30, message="location can be up to 30 characters")
    private String location;
    
    @NotNull
    private Boolean isActive;
    
    @NotNull
    private Boolean isAvailable;
    
    //one gender has many heroes but one hero has one gender
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gender_id")
	private Gender gender;
    
	//one hero rank has many heroes but one hero has one hero rank
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hero_rank_id")
	private HeroRank heroRank;
	
	// one hero specialty has many heroes, but one hero has one specialty
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hero_specialty_id")
	private HeroRank heroSpecialty;
    
	//a hero can upload many images but each image has one uploader
	@OneToMany(mappedBy="uploader", fetch = FetchType.LAZY)
	private List<UploadedImage> images;
	
	//a hero can have a profile picture, but does not have to, and not all pictures are profile pictures
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_image_id")
	private UploadedImage profileImage;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    //constructor and special
    public HeroUser() {}
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeroLicenseNumber() {
		return heroLicenseNumber;
	}

	public void setHeroLicenseNumber(String heroLicenseNumber) {
		this.heroLicenseNumber = heroLicenseNumber;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public String getHeroPower() {
		return heroPower;
	}

	public void setHeroPower(String heroPower) {
		this.heroPower = heroPower;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPhilosophy() {
		return philosophy;
	}

	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<UploadedImage> getImages() {
		return images;
	}

	public void setImages(List<UploadedImage> images) {
		this.images = images;
	}

	public UploadedImage getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(UploadedImage profileImage) {
		this.profileImage = profileImage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public HeroRank getHeroRank() {
		return heroRank;
	}

	public void setHeroRank(HeroRank heroRank) {
		this.heroRank = heroRank;
	}

	public HeroRank getHeroSpecialty() {
		return heroSpecialty;
	}

	public void setHeroSpecialty(HeroRank heroSpecialty) {
		this.heroSpecialty = heroSpecialty;
	}
}
