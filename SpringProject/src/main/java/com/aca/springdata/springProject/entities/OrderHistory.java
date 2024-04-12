package com.aca.springdata.springProject.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_history")
public class OrderHistory {
	
	@Id
	@GenericGenerator(name = "order_generator", type = com.aca.springdata.springProject.generators.CustomOrderIdGenerator.class)
	@GeneratedValue(generator = "order_generator")
	@Column(name = "ORDER_ID")
	private BigDecimal orderId;
	@Column(name = "ORDER_DATE")
	private Timestamp orderDate;
	@Column(name = "PRODUCT_IDS")
	private String products;
	@Column(name = "PRODUCT_QUANTITY")
	private String productsQuantity;
	@Column(name = "TOTAL")
	private double total;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	/*@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;*/

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getProductsQuantity() {
		return productsQuantity;
	}

	public void setProductsQuantity(String productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
