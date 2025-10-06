package com.ems.utils;

import java.time.LocalDate;
import java.util.List;

import com.ems.core.Employee;
import com.ems.core.FullTimeEmployee;
import com.ems.core.PartTimeEmployee;

public class EMSValidation {

	// public static String

	//fte
	public static FullTimeEmployee ValidateAllInputsFTE(String name, String doj, String phoneNumber, String aadhaarNumber,
			double monthlySalary, List<Employee> e1) throws EMSCustomException {
		
		LocalDate doj1 = LocalDate.parse(doj);
		
		
		checkAdharNumber(aadhaarNumber);
		checkDuplicateAadhaarNumber(aadhaarNumber, e1);
		checkForLocalDate(doj);
		checkPhoneNumber(phoneNumber);
		
		
		return new FullTimeEmployee(name, doj1, phoneNumber, aadhaarNumber, monthlySalary);
	}

	// pte
	public static PartTimeEmployee ValidateAllInputsPTE(String name, String doj, String phoneNumber, String aadhaarNumber,
			double hourlyPaymentAmount, List<Employee> e1) throws EMSCustomException {
		
		
		LocalDate doj1 = LocalDate.parse(doj);
		
		checkAdharNumber(aadhaarNumber);
		checkDuplicateAadhaarNumber(aadhaarNumber, e1);
		LocalDate date = checkForLocalDate(doj);
		checkPhoneNumber(phoneNumber);
		
		return new PartTimeEmployee(name, date, phoneNumber, aadhaarNumber,hourlyPaymentAmount);
	}
	// Date of joining should be of type LocalDate

	public static LocalDate checkForLocalDate(String doj) throws EMSCustomException {

		LocalDate d1 = LocalDate.parse(doj);
	
		return d1;
	}

	// Phone number should be all numeric and of length 10
	public static String checkPhoneNumber(String phoneNumber) throws EMSCustomException {
		String regexp = "^[0-9]{10}$";
		if (!(phoneNumber.matches(regexp))) {
			throw new EMSCustomException("Phone number is Invalid");
		}
		return null;
	}

	// Aadhaar number should be all numeric and of length 12 only, there should not
	// be any space in between.
	public static String checkAdharNumber(String aadhaarNumber) throws EMSCustomException {
		String regexp = "^[1-9][0-9]{11}$";
		if (!(aadhaarNumber.matches(regexp))) {
			throw new EMSCustomException("Aadhaar number is Invalid");
		}
		return null;
	}

	// Two employees cannot have same aadhaar number
	public static String checkDuplicateAadhaarNumber(String aadhaarNumber, List<Employee> e1)
			throws EMSCustomException {
		boolean found = e1.stream().anyMatch(p->p.getAadhaarNumber().equalsIgnoreCase(aadhaarNumber));
		if(found)
		{
			throw new EMSCustomException("duplicate AadhaarCard");
		}
		return null;
	}

}
