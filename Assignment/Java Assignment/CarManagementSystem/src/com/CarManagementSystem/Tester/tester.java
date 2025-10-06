package com.CarManagementSystem.Tester;

import java.util.Scanner;

import com.CarManagementSystem.services.CarManagementServiceImplementation;
import com.CarManagementSystem.services.CarManagementServices;

public class tester {
	public static void main(String[] args) {

		CarManagementServices service = new CarManagementServiceImplementation();

		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			int ch;
			while (!exit) {
				System.out.println("Enter choice:\n1. Add new car" + "\n2.display all car"
						+ "\n3.display car sorted by car id" + "\n4.find car with highest mileage"
						+ "\n5.remove car not available" + "\n6.update car price by brand name"
						+ "\n7.sort car price in descending order" + "\n8.Exit");
				try {
					switch (sc.nextInt()) {
					case 1: {
						service.addCar("City", "Honda", 1200000, 18.5, true, "sedan");
						service.addCar("Creta", "Hyundai", 1650000, 16.2, true, "suv");
						service.addCar("Swift", "Maruti", 850000, 22.1, false, "hatchback");
						service.addCar("Model3", "Tesla", 3500000, 0.0, true, "electric");
						service.addCar("Safari", "Tata", 2100000, 14.5, false, "suv");

						System.out.println("Car added...");
					}
						break;

					case 2: {
						service.displayAllCar();
					}
						break;

					case 3: {
						System.out.println("Sorted by car id:");
						service.displayCarInSortedByCarId();
					}
						break;

					case 4: {
						System.out.println("Car with highest mileage:");
						service.findCarWithHighestMileage();
					}
						break;

					case 5: {
						service.removeUnavailableCar();
					}
						break;

					case 6: {
						System.out.println("Enetr brand:");
						String brand = sc.next();
						System.out.println("Enter new Price:");
						double price = sc.nextDouble();
						service.updateCarPriceByBrand(brand, price);
					}
						break;

					case 7: {
						service.sortByPrice();
					}
						break;

					case 8: {
						System.out.println("Exit...");
						exit = true;
					}
						break;

					default:
						System.out.println("Invalid input...");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
