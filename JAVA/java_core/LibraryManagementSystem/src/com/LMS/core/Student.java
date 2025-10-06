package com.LMS.core;

import java.time.LocalDate;

public class Student extends Members{
	private String courseName;
	private int yearOfStudy;
	public Student(String name, LocalDate dateOfMembership, String phoneNumber, String aadhaarCard, String courseName,
			int yearOfStudy) {
		super(name, dateOfMembership, phoneNumber, aadhaarCard);
		this.courseName = courseName;
		this.yearOfStudy = yearOfStudy;
	}
	@Override
	public String toString() {
		return super.toString()+ " courseName=" + courseName + ", yearOfStudy=" + yearOfStudy+"]";
	}
	
	
}
