package com.IMS.core;

import java.util.Objects;

public class Inventory {
	private int id;
	private String name;
	private Category category;
	private double price;
	private int quantity;
	
	private static int idCounter;

	public Inventory(String name, Category category, double price, int quantity) {
		super();
		this.id = ++idCounter;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, name, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return category == other.category && id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	
	
	
}
