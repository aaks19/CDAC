package com.ProductManagement.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="products")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	@Column(name="product_name", length = 50, unique = true)
	private String productName;
	@Column(name="product_description", length = 50)
	private String productDescription;
	private LocalDate date;
	private Double price;
	@Column(name = "available_quantity")
	private Long availableQuantity;
	@Enumerated(EnumType.STRING)
	private Category category;
	
	
	public Product(String productName, String productDescription, LocalDate date, Double price,
			Long availableQuantity, Category category) {
		
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.date = date;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.category = category;
	}


	public Product(Long productId, String productName, Double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Long getAvailableQuantity() {
		return availableQuantity;
	}


	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", date=" + date + ", price=" + price + ", availableQuantity="
				+ availableQuantity + ", category=" + category + "]";
	}
	
	
}
