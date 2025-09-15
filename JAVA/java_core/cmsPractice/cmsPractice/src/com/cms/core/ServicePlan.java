package com.cms.core;

public enum ServicePlan {

	//enum constants
	BASIC(500), PRO(1000), PREMIUM(2000);
	
	//data members
	private int price;
	
	//constructor (CONSTRUCTOR PRIVATE)
	private ServicePlan(int price)
	{
		this.price=price;
	}
	
	//getter 
	int getPrice()
	{
		return this.price;
	}
	
	//setter
	void setPrice(int newPrice)
	{
		this.price = newPrice;
	}
	
	//toString() : display
	@Override
	public String toString()
	{
		return this.name().toUpperCase() +" " + this.price;
	}
	
}
