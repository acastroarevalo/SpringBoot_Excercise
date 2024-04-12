package com.aca.springdata.springProject.entities;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GenericGenerator(name = "user_generator", type = com.aca.springdata.springProject.generators.CustomUserIdGenerator.class)
	@GeneratedValue(generator = "user_generator")
	@Column(name = "USER_ID")
	private BigDecimal userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "BIO")
	private String bio;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "AREA_OF_INTEREST")
	private String areaOfInterest;
	@Column(name = "PWD")
	private String pwd;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<OrderHistory> orders;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Wishlist> wishes;

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
