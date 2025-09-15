package com.sms.services;

import java.time.LocalDate;
import java.util.List;

import com.sms.StudentManagementException.StudentManagementException;
import com.sms.core.Course;
import com.sms.core.Student;

public class SMSValidation {

	public static Student validateAll(String name, String email, int marks, String courses, String admissionDate,
			List<Student> studentList) throws StudentManagementException {
		validEmail(email);
		checkForDuplicate(email, studentList);
		Course validCourse = validateCourse(courses, marks);

		return new Student(name, email, marks, validCourse, LocalDate.parse(admissionDate));

	}

	public static void checkForDuplicate(String email, List<Student> studentList) throws StudentManagementException {
		// create instance of Student that wrap email
		Student stud = new Student(email);
		// check that the studentList contains the stud instance or not.
		if (studentList.contains(stud)) {
			throw new StudentManagementException("Email already exists...");
		}
	}

	public static void validEmail(String email) throws StudentManagementException {
		// declare a variable to store the email regular expression
		String regex = "^[a-z][a-z0-9._-]*@[a-z]+\\.(com|org|net)$";
		// check if the email is equal to the regular expression or not
		if (!email.matches(regex)) {
			// not matches then throw exception;
			throw new StudentManagementException("Invalid Email...");
		}
	}
	
	public static Course validateCourse(String course, int marks) throws IllegalArgumentException, StudentManagementException {
		Course myCourse = Course.valueOf(course.toUpperCase());
		if(myCourse.getMinMarks()>marks && myCourse.getMinSeat()==0) {	
				throw new StudentManagementException("Not eligible for the course");
		}
		myCourse.setMinSeat(myCourse.getMinSeat()-1);
		return myCourse;
	}
}
