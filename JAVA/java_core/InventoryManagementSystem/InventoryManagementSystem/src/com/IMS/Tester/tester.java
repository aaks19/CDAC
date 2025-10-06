package com.IMS.Tester;

import java.util.Scanner;

import com.IMS.Services.IMSService;
import com.IMS.Services.IMSServiceImplementation;

public class tester {
	public static void main(String[] args) {

		IMSService service = new IMSServiceImplementation();

		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			while (!exit) {
				try {
					System.out.println("1.Add Perishable Item\r\n" + "\r\n" + "2Add Non-Perishable Item\r\n" + "\r\n"
							+ "3.Delete Item by Item Code\r\n" + "\r\n" + "4.Search Item by Name\r\n" + "\r\n"
							+ "5.Display All Items\r\n" + "\r\n" + "6.Display Items Sorted by Price\r\n" + "\r\n"
							+ "7.Display Expired Items (for Perishable Items only)\r\n" + "\r\n" + "8.Exit");
					switch (sc.nextInt()) {
					case 1: {
//						String name, String category, double price, int quantity, String expiryDate
//						service.addPerishableItem("pen1", "Perishable", 20, 10, "2025-12-15");
//						service.addPerishableItem("pen2", "Perishable", 20, 10, "2025-08-15");
//						service.addPerishableItem("pen3", "Perishable", 20, 10, "2025-11-25");
//						service.addPerishableItem("pen4", "Perishable", 20, 10, "2026-01-30");
						String name = sc.next();
						String category = sc.next();
						double price = sc.nextDouble();
						int qty = sc.nextInt();
						String expdate = sc.next();
						service.addPerishableItem(name, category, price, qty, expdate);
//						service.addPerishableItem(sc.next(), sc.next(), sc.nextDouble(), sc.nextInt(), sc.next());
						System.out.println("Added...");
						break;
					}
					case 2: {
//						String name, String category, double price, int quantity, int warrentrPeriod
						String name = sc.next();
						String category = sc.next();
						double price = sc.nextDouble();
						int qty = sc.nextInt();
						int warrentyPeriod = sc.nextInt();
						service.addNonPerishableItem(name, category, price, qty, warrentyPeriod);
//						
//						service.addNonPerishableItem(sc.next(), sc.next(), sc.nextDouble(), sc.nextInt(), sc.nextInt());
						System.out.println("Added...");
						break;
					}
					case 3: {
						service.deleteItemByCode(sc.nextInt());
						break;
					}
					case 4: {
						service.searchItem(sc.next());
						break;
					}
					case 5: {
						service.displayAllItems();
						break;
					}
					case 6: {
						service.displayItemSortedByPrice();
						break;
					}
					case 7: {
						service.displayExpiredItem();
						break;
					}
					case 8: {
						System.out.println("Exit");
						exit = true;
						break;
					}
					default:
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
