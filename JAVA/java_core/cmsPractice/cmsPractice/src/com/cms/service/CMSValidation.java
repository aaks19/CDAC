package com.cms.service;

import java.time.LocalDate;
import java.util.List;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;

public class CMSValidation {

	//validate all inputs
	//public static customer
	public static  Customer validateAllInputs(String firstName, String lastName, String email, String password, int regAmount, LocalDate dob,
			ServicePlan plan,List<Customer> list) throws CMSHandlingException
	{
		//write all validate methods
		checkForDuplicate(email, list);
		checkForEmailValidation(email);
		
		//return new customer
		return new Customer(firstName, lastName, email, password, regAmount, dob, plan);
	}
	
	
	//public static void 
	public static void checkForDuplicate(String email,List<Customer> list) throws CMSHandlingException
	{
		//have to create single argument constructor first
		Customer wrapEmail = new Customer(email);
		//contains(obj): internally equals method call reference , override equals method also.
		if(list.contains(wrapEmail))
		{
			//true
			throw new CMSHandlingException("Same Email, please change email!!!");
		}
	}
	
	//validate email
	//pubic static void 
	public static void checkForEmailValidation(String email) throws CMSHandlingException
	{
		String regex =  "^[a-z][a-z0-9._-]*@[a-z]+\\.(com|org|net)$";
		if(email.matches(regex))
		{
			//true : email is correct
			System.out.println("Email is correct please continue...");
		}
		else
		{
			throw new CMSHandlingException("Invalid email, please type correctly!!");
		}
	}
}
