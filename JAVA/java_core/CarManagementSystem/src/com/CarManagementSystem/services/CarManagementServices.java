package com.CarManagementSystem.services;

import com.CarManagementSystem.Exception.CarManagementException;
import com.CarManagementSystem.core.CarType;

public interface CarManagementServices {
	
	String addCar(String modelName, String brand, double price, double mileage, boolean isAvailable,
			String car_type) throws CarManagementException;
	
	void displayAllCar();
	
	void displayCarInSortedByCarId();
	
	void findCarWithHighestMileage() throws CarManagementException;
	
	void removeUnavailableCar()throws CarManagementException;
	
	void updateCarPriceByBrand(String brand, double price) throws CarManagementException;
	
	void sortByPrice();
}
