package com.eshop.core;

public class Product {
	private int id;
	private String name;
	private String category;
	private int stock;
	private double price;

	public Product(int id, String name, String category, 
			int stock, double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.stock = stock;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", stock=" + stock + ", price="
				+ price + "]";
	}
	
	//Override equals
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			if(this.id == ((Product)obj).id && this.category == ((Product)obj).category)
			return this.id == ((Product)obj).id;
			
			
//			//mam's code
//			Product p = (Product)obj;
//			return (this.id == p.id) && (this.category.equals(p.category));
		}
		return false;
	}
	
	
	
	//Override hashCode
	@Override
	public int hashCode() {
		return ((Integer)id).hashCode() + (category.hashCode());
	}

}
