package com.CarManagementSystem.core;

import java.util.Objects;

public class Car {
	private int car_id;
	private String modelName;
	private String Brand;
	private double price;
	private double mileage;
	private boolean isAvailable;
	private CarType car_type;
	
	private static int idcounter;

	public Car(String modelName, String brand, double price, double mileage, boolean isAvailable,
			CarType car_type) {
		super();
		this.car_id = ++idcounter;
		this.modelName = modelName;
		this.Brand = brand;
		this.price = price;
		this.mileage = mileage;
		this.isAvailable = isAvailable;
		this.car_type = car_type;
	}
	
	
	
	public Car(double price) {
		this.price = price;
	}
	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public CarType getCar_type() {
		return car_type;
	}

	public void setCar_type(CarType car_type) {
		this.car_type = car_type;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(Brand, other.Brand) && car_id == other.car_id && car_type == other.car_type
				&& isAvailable == other.isAvailable
				&& Double.doubleToLongBits(mileage) == Double.doubleToLongBits(other.mileage)
				&& Objects.equals(modelName, other.modelName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ", modelName=" + modelName + ", Brand=" + Brand + ", price=" + price
				+ ", mileage=" + mileage + ", isAvailable=" + isAvailable + ", car_type=" + car_type + "]";
	}
	
	
	
	
	
	
}
