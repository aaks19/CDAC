package com.cms.core;

import java.time.LocalDate;

public class Customer {

	// has-a Relationship DataMembers
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int registerAmount;
	private LocalDate dob;
	private ServicePlan plan;

	// autoIncrement dataMember
	private static int idCounter = 0;

	// constructor
	public Customer(String firstName, String lastName, String email, String password, int registerAmount, LocalDate dob,
			ServicePlan plan) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.registerAmount = registerAmount;
		this.dob = dob;
		this.plan = plan;
		this.idCounter = ++idCounter;
	}

	public Customer(String email) {
		this.email = email;
	}

	// toString() : display.
	@Override
	public String toString() {
		return "id: " + (idCounter) + "name: " + firstName + " " + lastName + " email: " + email + " pass: " + password
				+ " registerAmount: " + registerAmount + " dob" + dob + " plan" + plan;
	}

	// getter And Setter

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRegisterAmount() {
		return registerAmount;
	}

	public void setRegisterAmount(int registerAmount) {
		this.registerAmount = registerAmount;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public ServicePlan getPlan() {
		return plan;
	}

	public void setPlan(ServicePlan plan) {
		this.plan = plan;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public boolean equals(Object obj)
	{
		System.out.println("equal method call");
		
		//run time error solve instanceof use
		if(obj instanceof Customer) {
		//downcasting for compile time error solve
		return this.email.equals(((Customer)obj).email);
		}
		//else retrun false
		return false;
	}

}
