package com.cms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;

public class CMSServiceImpl implements CMSService {

	// DataStructure use: ArrayList
	private List<Customer> guest;

	// constructor
	public CMSServiceImpl() {
		// ArrayList
		// list = any type of list interface
		this.guest = new ArrayList<>(1000);

		// Add Elements in ArrayList
		guest.add(new Customer("Raj", "Sharma", "amit@gmail.com", "amit@123", 1000, LocalDate.of(1990, 5, 12),
				ServicePlan.BASIC));
		guest.add(new Customer("Priya", "Verma", "priya@gmail.com", "priya@123", 2000, LocalDate.of(1995, 8, 23),
				ServicePlan.PRO));
	}

	// 1) Register Customer
	@Override
	public String registerCustomer(String firstName, String lastName, String email, String password, int registerAmount,
			LocalDate dob, ServicePlan plan) throws CMSHandlingException {
		// validate all inputs
		Customer cust = CMSValidation.validateAllInputs(firstName, lastName, email, password, registerAmount, dob, plan,
				guest);
		// validate succ comes this line
		// add elements into arryList
		guest.add(cust);
		// return message
		return "Registration is Successfull........";
	}

//	2) Display all customers
	@Override
	public void display() {
		for (Customer c : guest) {
			System.out.println(c);
		}
	}

//	3) Sign-in
	@Override
	public Customer signin(String email, String password) throws CMSHandlingException {
		Customer c = new Customer(email);
		// get index of the email from customers arraylist

		int index = guest.indexOf(c);

		// here index = -1 represents that the email is not present in the guest list
		if (index == -1) {
			throw new CMSHandlingException("Invalid email");
		}

		// the email is present ...now we have to check the password is equal to the
		// password that is saved in the arraylist
		Customer completeDetails = guest.get(index);
		if (completeDetails.getPassword().equals(password)) {
			return completeDetails;
		} else {
			throw new CMSHandlingException("password not matches with email");
		}
	}
	
	
//	4)Change password
	
	public String changePassword(String email, String oldPassword, String newPassword) throws CMSHandlingException{
		//first check if the customer is vaild or not
		Customer validCustomer = signin(email, oldPassword);
		validCustomer.setPassword(newPassword);
		return "Password Updated";
	}
	
//	5)Unsubscribe the customer
	
	public String unsubscribeCustomer(String email) throws CMSHandlingException{
//		Delete the customer by the email
//		wrap email in Customer c
		Customer c = new Customer(email);
		if(guest.remove(c)){
			return "Un-subscribed successful";
		}
		throw new CMSHandlingException("Email not exist");
		
	}

}
