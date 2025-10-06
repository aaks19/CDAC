package com.cms.test;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;
import com.cms.service.CMSValidation;

public class test2 {

	public static void main(String[] args) throws CMSHandlingException {
		
		ArrayList<Customer> arr = new ArrayList<>(10);
		
		arr.add(new Customer("Raj", "Sharma", "amit@gmail.com", "amit@123", 1000, LocalDate.of(1990, 5, 12),
				ServicePlan.BASIC));
		
		CMSValidation.checkForEmailValidation("amitgmail.com");
	}
}
