package com.ems.core;

import java.time.LocalDate;

public class FullTimeEmployee extends Employee {
	// datamember
	// For FTE, monthly salary is also captured.
	private double monthlySalary;
	

	// constructor
	public FullTimeEmployee(String name, LocalDate doj, String phoneNumber, String aadhaarNumber,
			double monthlySalary) {
		super(name, doj, phoneNumber, aadhaarNumber);
		this.monthlySalary = monthlySalary;
	}

	// getter and setters
	public double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	// tostring

	@Override
	public String toString() {
		return "FullTimeEmployee [monthlySalary=" + monthlySalary + ", toString()=" + super.toString() + "]";
	}


}
