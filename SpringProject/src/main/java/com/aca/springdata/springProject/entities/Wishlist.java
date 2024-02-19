package com.aca.springdata.springProject.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist {

	@Id
	@GenericGenerator(name = "wish_generator", type = com.aca.springdata.springProject.generators.CustomIdGenerator.class)
	@GeneratedValue(generator = "wish_generator")
	@Column(name = "WISH_ID")
	private BigDecimal wishId;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	public BigDecimal getWishId() {
		return wishId;
	}

	public void setWishId(BigDecimal wishId) {
		this.wishId = wishId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
