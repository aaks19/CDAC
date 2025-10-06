package com.LMS.Tester;

import java.util.Scanner;

import com.LMS.service.LMSService;
import com.LMS.service.LMSServiceImplementation;

public class tester {
	public static void main(String[] args) {

		LMSService service = new LMSServiceImplementation();
		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;
			while (!exit) {
				System.out.println("Enter choice:" + "\n1. Add Student" + "\n2. Add Faculty" + "\n3. Add book"
						+ "\n4. Issue book to member by bookid and memberid" + "\n5. return a book by bookid"
						+ "\n6. delete a member by memberid" + "\n7. search member by aadhaar card"
						+ "\n8. Display all members" + "\n9.desplay all books sorted by title" + "\n10. Exit");

				try {
					switch (sc.nextInt()) {
					case 1: {
						service.addStudent("Ravi", "2023-06-10", "9876543210", "123456789012", "Computer Science", 3);

						service.addStudent("Neha", "2022-03-15", "9123456789", "234567890123", "Electrical Engineering", 2);

						service.addStudent("Amit", "2024-02-05", "9988776655", "345678901234", "Mechanical Engineering", 4);

						service.addStudent("Priya", "2021-11-20", "9090909090", "456789012345", "Information Technology", 1);

						service.addStudent("Rahul", "2023-09-01", "8080808080", "567890123456", "Civil Engineering", 2);
						
						System.out.println("Student added...");

						break;
					}
					case 2: {
						service.addFaculty("Dr. Sharma", "2020-08-01", "9090909090", "678901234567", "Physics", "Professor");

						service.addFaculty("Prof. Meena", "2021-11-20", "8080808080", "789012345678", "Mathematics", "Assistant Professor");

						service.addFaculty("Dr. Rakesh", "2019-05-12", "7007007007", "890123456789", "Computer Science", "Head of Department");

						service.addFaculty("Dr. Nisha", "2022-09-10", "9112233445", "901234567890", "Chemistry", "Associate Professor");

						service.addFaculty("Prof. Anil", "2023-02-28", "9556677889", "912345678901", "Mechanical", "Lecturer");

						System.out.println("Faculty added...");
						break;
					}
					case 3: {
						
						service.addBook("Java Programming", "James Gosling", 550.0, true);

						service.addBook("Data Structures and Algorithms", "Mark Allen Weiss", 499.0, true);

						service.addBook("Database Systems", "Raghu Ramakrishnan", 750.0, true);

						service.addBook("Artificial Intelligence: A Modern Approach", "Stuart Russell", 899.0, true);

						service.addBook("Operating System Concepts", "Abraham Silberschatz", 650.0, true);

						System.out.println("Books added...");
						break;
					}
					case 4: {
						System.out.println("Enter member id and book id:");
						service.issueBookToMember(sc.nextInt(), sc.nextInt());
						break;
					}
					case 5: {
						System.out.println("Enter book id");
						service.returnBook(sc.nextInt());
						break;
					}
					case 6: {
						System.out.println("Enter Member Id");
						service.deleteMember(sc.nextInt());;
						break;
					}
					case 7: {
						System.out.println("Enter Aadhaar Number");
						service.searchMemberByAadhaar(sc.next());
						break;
					}
					case 8: {
						service.displayAllMember();
						break;
					}
					case 9: {
						service.displayAllBookSortedByTitle();
						break;
					}
					case 10: {
						System.out.println("Exit...");
						exit=true;
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
