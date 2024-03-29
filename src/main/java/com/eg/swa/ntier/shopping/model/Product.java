package com.eg.swa.ntier.shopping.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer quantity;
	private BigDecimal price;

	private Boolean isDeleted;

	// constructors, getters and setters

	public Product() {

	}

	public Product(Long productId) {
		this.id = productId;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}
}
