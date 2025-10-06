/**
 * 
 */
package com.ems.service;

import java.time.LocalDate;

import com.ems.utils.EMSCustomException;

/**
 * 
 */
public interface EMSService {

	// public string

	// Application Menu:
//1)	Add full time employee
	public String addFullTimeEmployee(String name, String doj, String phoneNumber, String aadhaarNumber,
			double monthlySalary) throws EMSCustomException;

//	Add part time employee
	public String addPartTimeEmployee(String name, String doj, String phoneNumber, String aadhaarNumber,
			double hourlyPaymentAmount) throws EMSCustomException;

//	Delete an employee by Emp Id
	public String DeleteEmployeeById(int id) throws EMSCustomException;

//	Search employee details by Aadhaar number
	public String SearchAadhaarNumber(String aadhaarNumber) throws EMSCustomException;
//	Display all employee details
	public void displayEmployee();
//	Display all employee details sorted by date of joining
	public void displayEmployeeSortedByDoj();

//	Exit
}
