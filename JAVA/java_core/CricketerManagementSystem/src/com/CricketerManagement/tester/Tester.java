package com.CricketerManagement.tester;

import java.util.Scanner;

import com.CricketerManagement.services.CricketerManagementService;
import com.CricketerManagement.services.CricketerServiceImplementation;

public class Tester {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			CricketerManagementService service = new CricketerServiceImplementation();
			
			boolean exit = false;
			
			while(!exit) {
				try {
					System.out.println("Enter Choice:\n1.Add Cricketer\n2.Modify Cricketer's rating\n3.Search Cricketer by name\n"
							+ "4. Display all Cricketers added in collection.\n5.Display All Cricketers in sorted form by rating.");
					
					switch (sc.nextInt()) {
					case 1: {
						service.addCricketer("Akshat", 22, "akshat.verma@gmail.com", "9993247700", 8.7);
						service.addCricketer("John", 28, "john123@yahoo.com", "9876543210", 7.5);
						service.addCricketer("Ravi", 30, "ravi.kumar@company.org", "9123456780", 9.1);
						service.addCricketer("Neha", 25, "neha_mehta@outlook.net", "9988776655", 8.0);
						service.addCricketer("Aarav", 21, "a@abc.com", "9111122233", 6.8);

						break;
					}
					
					case 2: {
						System.out.println("Modify Rating:");
						System.out.println("Enter email and new rating:");
						service.modifyRating(sc.next(), sc.nextDouble());
						break;
					}
					
					case 3: {
						System.out.println("Search Cricketer:");
						System.out.println("Enter name: ");
						service.searchCricketer(sc.next());
						break;
					}
					
					case 4: {
						service.displayAllCricketer();
						break;
					}
					
					case 5: {
						System.out.println("In sorted order of rating...");
						service.DisplaySortedByRating();
						break;
					}
					
					case 0: {
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + sc.nextInt());
					}
					
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
