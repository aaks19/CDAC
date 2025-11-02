package com.ems.core;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

//datamembers
	// Emp Id should be auto generated and unique for each employee.
	private int id;
	private static int idCounter;

	private String name;
	private LocalDate Doj;
	private String phoneNumber;
	private String aadhaarNumber;

	// constructor
	public Employee(String name, LocalDate doj, String phoneNumber, String aadhaarNumber) {
		super();
		this.name = name;
		this.Doj = doj;
		this.phoneNumber = phoneNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.id = ++idCounter;
	}

	// constructor2
	public Employee(LocalDate doj) {
		super();
		this.Doj = doj;
	}

	// getter and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDoj() {
		return Doj;
	}

	public void setDoj(LocalDate doj) {
		Doj = doj;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// tostring

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", Doj=" + Doj + ", phoneNumber=" + phoneNumber
				+ ", aadhaarNumber=" + aadhaarNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Doj, aadhaarNumber, name, phoneNumber);
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(Doj, other.Doj) && Objects.equals(aadhaarNumber, other.aadhaarNumber)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}

}
