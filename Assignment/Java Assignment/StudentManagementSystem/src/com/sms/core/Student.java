package com.sms.core;

import java.time.LocalDate;

public class Student implements Comparable<Student>{
	
//	-id (auto-incremented)
//	- name(string)
//	- email (unique)
//	- marks (int)
//	- course (enum)
//	- admissionDate(LocalDate)
	
	private int id;
	private String name;
	private String email;
	private int marks;
	private Course courses;
	private LocalDate admissionDate;
	private static int idcounter;

	public Student(String name, String email, int marks, Course courses, LocalDate admissionDate) {
		this.id = ++idcounter;
		this.name = name;
		this.email = email;
		this.marks = marks;
		this.courses = courses;
		this.admissionDate = admissionDate;
	}
	
	public Student(String email) {
		this.email = email;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public int getMarks() {
		return marks;
	}




	public void setMarks(int marks) {
		this.marks = marks;
	}




	public Course getCourses() {
		return courses;
	}




	public void setCourses(Course courses) {
		this.courses = courses;
	}




	public LocalDate getAdmissionDate() {
		return admissionDate;
	}




	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}




	@Override
	public String toString() {
		return "Student Details: Id: "+id+" name: "+name+" email: "+email+" marks: "+marks+" course: "+courses+" Admission Date: "+admissionDate +"\n\n------------------------------------------------------";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student s = (Student)obj;
			return this.email.equals(s.email);
		}
		return false;
	}

	@Override
	public int compareTo(Student anotherStudent) {
		
		return this.email.compareTo(anotherStudent.email);
	}
	
	
}
