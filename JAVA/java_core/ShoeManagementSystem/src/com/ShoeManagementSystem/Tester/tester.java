package com.ShoeManagementSystem.Tester;

import java.util.Scanner;

import com.ShoeManagementSystem.services.ShoeManagementService;
import com.ShoeManagementSystem.services.ShoeManagementServiceImplementation;

public class tester {
	public static void main(String[] args) {
		
		ShoeManagementService service = new ShoeManagementServiceImplementation();
		
		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			int choice;

			while (!exit) {
				try {
					System.out.println("Enter choice:\n1.Add a new Shoe" + "\n2.Display all shoe"
							+ "\n3.Display all shoe in sorted by shoe id" + "\n4.Search most expensive shoes"
							+ "\n5.remove shoes that are not available" + "\n6.update shoe price based on brand"
							+ "\n7.sort shoe id as per price in descending order" + "\n8.Exit");

					switch (sc.nextInt()) {
					case 1: {
						service.addNewShoe("AirZoom", "Nike", 9, 8999.50, true, "sports");
						service.addNewShoe("ClassicLeather", "Reebok", 8, 7499.00, true, "casual");
						service.addNewShoe("UltraBoost", "Adidas", 10, 11999.99, false, "sports");
						service.addNewShoe("FormalEdge", "Bata", 7, 4999.00, true, "formal");
						service.addNewShoe("EverydayComfort", "Sparx", 6, 3599.00, false, "casual");
						
						System.out.println("Added...");
					}
						break;

					case 2: {
						System.out.println("All shoes list");
						service.displayAllShoe();
					}
						break;

					case 3: {
						System.out.println("Sorted by shoe id");
						service.displayShoeSortedById();
					}
						break;

					case 4: {
						System.out.println("Expensive shoe:\n");
						service.searchMostExpensiveShoe();
					}
						break;

					case 5: {
						service.removeShoe();
					}
						break;

					case 6: {
						System.out.println("Enter brand: ");
						String brand = sc.next();
						System.out.println("Enter new price: ");
						double price = sc.nextDouble();
						service.updatePrice(brand, price);
						System.out.println("Price updated of brand : "+brand);
					}
						break;

					case 7: {
						System.out.println("Sorted by price in descending order");
						service.sortShoeByPriceDescending();
					}
						break;

					case 8: {
						System.out.println("Exit...");
						exit = true;
					}
						break;
					default:
						System.out.println("Invalid choice...");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}
}
