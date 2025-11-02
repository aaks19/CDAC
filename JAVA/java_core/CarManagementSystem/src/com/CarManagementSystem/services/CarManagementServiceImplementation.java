package com.CarManagementSystem.services;

import static com.CarManagementSystem.services.CarManagementValidation.validateAll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.CarManagementSystem.Exception.CarManagementException;
import com.CarManagementSystem.core.Car;

public class CarManagementServiceImplementation implements CarManagementServices {

	private List<Car> carList;
	
	public CarManagementServiceImplementation() {
		carList = new ArrayList<>();
	}
	
	@Override
	public String addCar(String modelName, String brand, double price, double mileage, boolean isAvailable,
			String car_type) throws CarManagementException {
		Car c = validateAll(modelName, brand, price, mileage, isAvailable, car_type, carList);
		carList.add(c);
		return null;
	}

	@Override
	public void displayAllCar() {
		carList.stream()
			   .forEach(p->System.out.println(p));

	}

	@Override
	public void displayCarInSortedByCarId() {
		Comparator<Car> comp = (c1,c2)->((Integer)c2.getCar_id()).compareTo(c1.getCar_id());
		carList.stream()
			   .sorted(comp)
			   .forEach(c->System.out.println(c));
	}

	@Override
	public void findCarWithHighestMileage() throws CarManagementException{
		Comparator<Car> comp = (c1,c2)->((Double)c2.getMileage()).compareTo(c1.getMileage());
		Car c = carList.stream()
					   .sorted(comp)
			           .findFirst()
			           .orElseThrow(()->new CarManagementException("Not Found"));
		
		System.out.println("Car found:\n"+c);
	}

	@Override
	public void removeUnavailableCar() {
		carList.removeIf(c->((Boolean)c.isAvailable()).equals(false));
		System.out.println("Removed un-available cars");
	}

	@Override
	public void updateCarPriceByBrand(String brand, double price) {
		carList.stream()
				.filter(c->c.getBrand().equals(brand))
				.forEach(c->c.setPrice(price));
		System.out.println("Price updated of brand : "+brand);
	}

	@Override
	public void sortByPrice() {
		Comparator<Car> comp = (c1,c2)->((Double)c2.getPrice()).compareTo(c1.getPrice());
		
		carList.stream()
			   .sorted(comp)
			   .forEach(c->System.out.println(c));

	}

}
