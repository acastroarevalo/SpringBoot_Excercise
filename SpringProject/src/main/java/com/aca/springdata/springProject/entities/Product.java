package com.aca.springdata.springProject.entities;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GenericGenerator(name = "product_generator", type = com.aca.springdata.springProject.generators.CustomIdGenerator.class)
	@GeneratedValue(generator = "product_generator")
	@Column(name = "PRODUCT_ID")
	private BigDecimal productId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PRICE")
	private double price;
	@Lob
	@Column(name = "IMAGE")
	private byte[] image;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TOTAL_PRODUCTS_INVENTORY")
	private long totalProductInventory;
	@Column(name = "STATUS")
	private boolean status;
	@OneToMany(mappedBy = "product")
	private List<OrderHistory> orders;
	@OneToMany(mappedBy = "product")
	private List<Wishlist> wishes;

	public BigDecimal getProductId() {
		return productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTotalProductInventory() {
		return totalProductInventory;
	}

	public void setTotalProductInventory(long totalProductInventory) {
		this.totalProductInventory = totalProductInventory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<OrderHistory> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderHistory> orders) {
		this.orders = orders;
	}

}
