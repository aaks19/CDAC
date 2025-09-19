package com.cms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;

public class CMSServiceImpl implements CMSService {

	// DataStructure use: ArrayList
	private Map<String, Customer> customerMap;

	// constructor
	public CMSServiceImpl() {
		// ArrayList
		// list = any type of list interface
		customerMap = new HashMap<>(1000);
		List<Customer> customerList = new ArrayList<>();
		// Add Elements in ArrayList
		customerList.add(new Customer("Raj", "Sharma", "amit@gmail.com", "amit@123", 1000, LocalDate.of(1990, 5, 12),
				ServicePlan.BASIC));
		customerList.add(new Customer("Priya", "Verma", "priya@gmail.com", "priya@123", 2000, LocalDate.of(1995, 8, 23),
				ServicePlan.PRO));
	}

	// 1) Register Customer
	@Override
	public String registerCustomer(String firstName, String lastName, String email, String password, int registerAmount,
			LocalDate dob, ServicePlan plan) throws CMSHandlingException {
		// validate all inputs
		Customer cust = CMSValidation.validateAllInputs(firstName, lastName, email, password, registerAmount, dob, plan,
				customerMap);
		// validate succ comes this line
		// add elements into arryList
		customerMap.put(cust.getEmail(), cust);
		// return message
		return "Registration is Successfull........";
	}

//	2) Display all customers
	@Override
	public void display() {
		for (Customer c : customerMap.values()) {
			System.out.println(c);
		}
	}

//	3) Sign-in
	@Override
	public Customer signin(String email, String password) throws CMSHandlingException {
//		Customer c = new Customer(email);
//		// get index of the email from customers arraylist
//
//		int index = customerMap.ge;
//
//		// here index = -1 represents that the email is not present in the customerMap list
//		if (index == -1) {
//			throw new CMSHandlingException("Invalid email");
//		}
//
//		// the email is present ...now we have to check the password is equal to the
//		// password that is saved in the arraylist
//		Customer completeDetails = customerMap.get(index);
//		if (completeDetails.getPassword().equals(password)) {
//			return completeDetails;
//		} else {
//			throw new CMSHandlingException("password not matches with email");
//		}
		
		Customer c = customerMap.get(email);
		if(c==null) {
			throw new CMSHandlingException("Invalid Email...");
		}
		if(!c.getPassword().equals(password)) {
			throw new CMSHandlingException("Invalid password...");
		}
		return c;
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
		if(customerMap.remove(c) != null){
			return "Un-subscribed successful";
		}
		throw new CMSHandlingException("Email not exist");
		
	}

}
