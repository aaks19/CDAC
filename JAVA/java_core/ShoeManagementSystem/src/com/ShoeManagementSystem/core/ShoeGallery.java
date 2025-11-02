package com.ShoeManagementSystem.core;

import java.util.Objects;

public class ShoeGallery {
	private int shoe_id;
	private String name;
	private String brand;
	private int rating;
	private double price;
	private boolean availableInGallery;
	private ShoeType shoe_type;
	
	private static int idCounter;

	public ShoeGallery(String name, String brand, int rating, double price, boolean availableInGallery,
			ShoeType shoe_type) {
		super();
		this.shoe_id = ++idCounter;
		this.name = name;
		this.brand = brand;
		this.rating = rating;
		this.price = price;
		this.availableInGallery = availableInGallery;
		this.shoe_type = shoe_type;
	}
	
	public ShoeGallery(int rating) {
		this.rating = rating;
	}
	
	

	public int getShoe_id() {
		return shoe_id;
	}

	public void setShoe_id(int shoe_id) {
		this.shoe_id = shoe_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailableInGallery() {
		return availableInGallery;
	}

	public void setAvailableInGallery(boolean availableInGallery) {
		this.availableInGallery = availableInGallery;
	}

	public ShoeType getShoe_type() {
		return shoe_type;
	}

	public void setShoe_type(ShoeType shoe_type) {
		this.shoe_type = shoe_type;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoeGallery other = (ShoeGallery) obj;
		return availableInGallery == other.availableInGallery && Objects.equals(brand, other.brand)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && rating == other.rating
				&& shoe_id == other.shoe_id && shoe_type == other.shoe_type;
	}

	@Override
	public String toString() {
		return "ShoeGallery [shoe_id=" + shoe_id + ", name=" + name + ", brand=" + brand + ", rating=" + rating
				+ ", price=" + price + ", availableInGallery=" + availableInGallery + ", shoe_type=" + shoe_type + "]";
	}
	
}
