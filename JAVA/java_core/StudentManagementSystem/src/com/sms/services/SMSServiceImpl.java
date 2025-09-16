package com.sms.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import com.sms.StudentManagementException.StudentManagementException;
import com.sms.core.Student;

import static com.sms.services.SMSValidation.*;

public class SMSServiceImpl implements SMSService {

	ArrayList<Student> students = new ArrayList<>();
	
	@Override
	public String takeAdmission(String name, String email, int marks, String course, String admissionDate) throws StudentManagementException {
		Student stud = validateAll(name, email, marks, course, admissionDate, students);
		students.add(stud);
		return "Registeration successful";
	}
	@Override
	public void displayAllStudnets() {
		for(Student s : students) {
			System.out.println(s);
		}
	}
	
	//cancle admission
	@Override
	public void cancleAdmission(String email) {
//		Student stud = new Student(email);
		Iterator<Student> itr = students.iterator();
		while(itr.hasNext()) {
			if(itr.next().getEmail().equals(email)) {
				Student stud = itr.next();
				stud.getCourses().setMinSeat(stud.getCourses().getMinSeat()+1);
				itr.remove();
			}
		}
		System.out.println("Successfully canceled admission");
	}
	
	//search student by email
	@Override
	public void searchStudentByEmail(String email) {
		ListIterator<Student> itr = students.listIterator();
		while(itr.hasNext()) {
			if(itr.next().getEmail().equals(email)) {
				System.out.println("Student found");
				Student stud = itr.previous();
				System.out.println(stud);
				return;
			}
			System.out.println("Student not found...");
		}
	}
	
	//sort By course;
	@Override
	public void listStudentByCourse(String courses) throws StudentManagementException {
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare()
		});
	}
}
