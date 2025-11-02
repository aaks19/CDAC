package com.cms.service;

import java.time.LocalDate;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;

public interface CMSService {

	// Service Layer: buissness logic : interface
	// string : toString
	//write exception
	String registerCustomer(String firstName, String lastName, String email, String password, int registerAmount, LocalDate dob, ServicePlan plan)
	throws CMSHandlingException;
	
	void display();
	
	//Sign-in Service declaration
	Customer signin(String email, String password) throws CMSHandlingException;
	String changePassword(String email, String oldPassword, String newPassword) throws CMSHandlingException;
	String unsubscribeCustomer(String email) throws CMSHandlingException;
}
