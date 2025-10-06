package com.ems.tester;

import java.time.LocalDate;
import java.util.Scanner;

import com.ems.service.EMSImpl;
import com.ems.service.EMSService;

public class Tester {

	public static void main(String[] args) {

		EMSService service = new EMSImpl();

		try (Scanner sc = new Scanner(System.in)) {

			boolean flag = false;

			while (!flag) {

				System.out.println("1Add full time employee\r\n" + "2Add part time employee\r\n"
						+ "3Delete an employee by Emp Id\r\n" + "4Search employee details by Aadhaar number\r\n"
						+ "5Display all employee details\r\n"
						+ "6Display all employee details sorted by date of joining\r\n" + "7Exit");

				try {
//...........................
					switch (sc.nextInt()) {
					case 1: {
						System.out.println(
								"String name, LocalDate doj, String phoneNumber, String aadhaarNumber,double monthlySalary");
						System.out.println("Add FullTimeEmployee");
						System.out.println(service.addFullTimeEmployee(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble()));

						break;
					}
					case 2: {
						System.out.println("String name, LocalDate doj, String phoneNumber, String aadhaarNumber,\r\n"
								+ "			double hourlyPaymentAmount");
						System.out.println("Add PartTimeEmployee");
						System.out.println(service.addFullTimeEmployee(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble()));

						break;
					}

					case 3: {
						System.out.println("Delete by id");
						System.out.println("Enter id to delete");
						service.DeleteEmployeeById(sc.nextInt());
						System.out.println("delete done");
						break;
					}
					case 4: {
						System.out.println("Search by aadharno");
						System.out.println("Enter aadharno to search:");
						service.SearchAadhaarNumber(sc.next());
						break;
					}
					case 5: {
						System.out.println("display");
						service.displayEmployee();
						break;
					}
					case 6: {
						System.out.println("display by doj");
						service.displayEmployeeSortedByDoj();
						break;
					}
					case 7:
						flag = true;
						break;
					default:
						System.out.println("invalid choice");
						break;
					}
// ...........................
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}
}
