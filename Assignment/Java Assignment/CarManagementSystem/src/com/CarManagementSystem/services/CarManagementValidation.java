package com.CarManagementSystem.services;

import java.util.List;

import com.CarManagementSystem.Exception.CarManagementException;
import com.CarManagementSystem.core.Car;
import com.CarManagementSystem.core.CarType;

public class CarManagementValidation {
	public static void checkPrice(double price) throws CarManagementException {
		if(price<=0) {
			throw new CarManagementException("Price cant be 0 or negative");
		}
	}
	
	public static void checkMileage(double mileage) throws CarManagementException{
		if(mileage<0) {
			throw new CarManagementException("Mileage cant be 0 or negative");
		}
	}
	
	public static void checkModelName(String name) throws CarManagementException{
		if(name.length()<3 || name.length()>20) {
			throw new CarManagementException("Invalid name");
		}
	}
	
	public static CarType checkCarType(String car_type) throws IllegalArgumentException{
		CarType type = CarType.valueOf(car_type.toUpperCase());
		return type;
	}
	
	public static Car validateAll(String modelName, String brand, double price, double mileage, boolean isAvailable,
			String car_type, List<Car> carList) throws CarManagementException{
		checkMileage(mileage);
		checkModelName(modelName);
		checkPrice(price);
		
		CarType cartype = checkCarType(car_type);
		
		return new Car(modelName, brand, price, mileage, isAvailable, cartype);
	}
}
