package com.sms.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.sms.core.Course;
import com.sms.services.SMSService;
import com.sms.services.SMSServiceImpl;

public class Tester {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			SMSService service = new SMSServiceImpl();
			boolean exit = false;

			while (!exit) {
				try {
					System.out.println("1.Register Student\n2.Display Student Details\n3.Cancle Admission\n0.Exit");
					switch (sc.nextInt()) {
					case 1: {
						// ✅ Valid case (meets minMarks, correct email, valid course)
						service.takeAdmission("Rahul Sharma", "rahul.sharma@gmail.com", 85, "python", "2022-01-15");

						// ✅ Valid case (different course, valid email)
						service.takeAdmission("Priya Verma", "priya.verma@yahoo.com", 95, "PYTHON", "2022-03-10");

						service.takeAdmission("Neha Kapoor", "neha.kapoor@gmail.com", 91, "python", "2023-08-28");

						// ❌ Invalid email (uppercase start)
						service.takeAdmission("Amit Kumar", "Amit.kumar@gmail.com", 76, "DBT", "2022-05-05");

						// ❌ Invalid email (bad domain)
						service.takeAdmission("Sneha Patel", "sneha.patel@gmail.in", 88, "WEB_JAVA", "2022-07-20");

						// ❌ Duplicate email (same as Rahul above → should trigger duplicate check)
						service.takeAdmission("Karan Singh", "rahul.sharma@gmail.com", 67, "MERN", "2022-09-25");

						// ❌ Course not in enum (should throw IllegalArgumentException)
						service.takeAdmission("Meera Iyer", "meera.iyer@gmail.com", 95, "DATA_SCIENCE", "2022-11-30");

						// ❌ Marks below minMarks but seats available → should pass
						service.takeAdmission("Vikas Gupta", "vikas.gupta@gmail.com", 45, "MERN", "2023-02-12");

						// ❌ Marks below minMarks and seat = 0 → should throw Not eligible
						// (set MERN seats = 0 before running this)
						service.takeAdmission("Ananya Joshi", "ananya.joshi@yahoo.com", 30, "MERN", "2023-04-18");

						// ❌ Invalid date format (will throw DateTimeParseException)
						service.takeAdmission("Suresh Reddy", "suresh.reddy@gmail.com", 90, "WEB_JAVA", "23-06-2023");

						// ✅ Valid high boundary (edge case: exactly minMarks)
						service.takeAdmission("Neha Kapoor", "neha.kapoor@gmail.com", 91, "WEB_JAVA", "2023-08-28");

					}
						break;

					case 2: {
						service.displayAllStudnets();
					}
						break;
						
					case 3: {
						System.out.println("Enter email of student to cancle admission");
						service.cancleAdmission(sc.next());;
					}
						break;
					
					case 4:{
						System.out.println("Enter email of student to search");
						service.searchStudentByEmail(sc.next());
					}
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
