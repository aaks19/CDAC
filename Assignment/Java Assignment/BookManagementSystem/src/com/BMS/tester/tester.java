package com.BMS.tester;

import java.util.Scanner;

import com.BMS.Exception.BMSException;
import com.BMS.service.BMSService;
import com.BMS.service.BMSServiceImplementation;

public class tester {
	public static void main(String[] args) {
		BMSService service = new BMSServiceImplementation();

		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			int ch;
			while (!exit) {
				try {
					System.out.println(
							"Enter choice:\n1.Add Book.\n2.Update Price.\n3.Search.\n4.display.\n5.sort.\n0.Exit");
					;

					switch (sc.nextInt()) {
					case 1: {
						service.addBook("mera", "Akshat", "254152563", 2500.00, 9.8);
						service.addBook("Java", "Priya", "785412369", 1500.00, 8.5);
						service.addBook("DSA", "Aryan", "963258741", 2000.00, 9.2);
						service.addBook("Algorithms", "Sneha", "147852369", 1800.00, 8.9);
						service.addBook("DesignPatterns", "Vikram", "258963147", 2200.00, 9.5);
						service.addBook("CleanCode", "Anjali", "369741852", 3000.00, 9.7);

						System.out.println("Added...");
					}
						break;

					case 2: {
						System.out.println("enter isbn number: ");
						String isbn = sc.next();
						System.out.println("Enter new price: ");
						double price = sc.nextDouble();
						
						service.updatePrice(isbn, price);
						System.out.println("updated...");
					}
						break;

					case 3: {
						System.out.println("Enter title: ");
						String tite = sc.next();
						
						service.searchByTitle(tite);
					}
						break;

					case 4: {
						service.displayAllBooks();
					}
						break;

					case 5: {
						service.displayBooksSorted();
					}
						break;

					case 0: {

					}
						break;

					default:
						throw new IllegalArgumentException("Unexpected value: " + sc.nextInt());
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
