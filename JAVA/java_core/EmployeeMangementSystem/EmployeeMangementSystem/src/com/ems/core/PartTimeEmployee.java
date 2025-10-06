package com.ems.core;

import java.time.LocalDate;

public class PartTimeEmployee extends Employee {

	// For PTE, hourly payment amount is also captured.
	// datamembers
	private double hourlyPaymentAmount;

	// costructor
	public PartTimeEmployee(String name, LocalDate doj, String phoneNumber, String aadhaarNumber,
			double hourlyPaymentAmount) {
		super(name, doj, phoneNumber, aadhaarNumber);
		this.hourlyPaymentAmount = hourlyPaymentAmount;
	}

	// getter and setters

	public double getHourlyPaymentAmount() {
		return hourlyPaymentAmount;
	}

	public void setHourlyPaymentAmount(double hourlyPaymentAmount) {
		this.hourlyPaymentAmount = hourlyPaymentAmount;
	}
	// tostring

	@Override
	public String toString() {
		return "PartTimeEmployee [hourlyPaymentAmount=" + hourlyPaymentAmount + ", toString()=" + super.toString()
				+ "]";
	}

}
