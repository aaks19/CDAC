package com.cms.test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;
import com.cms.service.CMSValidation;

public class test1 {

	public static void main(String[] args) throws CMSHandlingException{
		Map<String, Customer> cust = new HashMap<>();
		cust.add(new Customer("Raj", "Sharma", "amit@gmail.com", "amit@123", 1000, LocalDate.of(1990, 5, 12),
				ServicePlan.BASIC));
		cust.add(new Customer("Priya", "Verma", "priya@gmail.com", "priya@123", 2000, LocalDate.of(1995, 8, 23),
				ServicePlan.PRO));
		CMSValidation.checkForDuplicate("amit@gmail.com", cust);
	}
}
