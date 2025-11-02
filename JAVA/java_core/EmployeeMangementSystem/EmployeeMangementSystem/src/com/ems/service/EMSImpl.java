package com.ems.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ems.core.Employee;
import com.ems.core.FullTimeEmployee;
import com.ems.core.PartTimeEmployee;
import com.ems.utils.EMSCustomException;
import com.ems.utils.EMSValidation;



public class EMSImpl implements EMSService
{
	
	//dataStructure
	List<Employee>emplist;

	public EMSImpl() {
		
		this.emplist  = new ArrayList<>();
	}

	//1) add Emp
	@Override
	public String addFullTimeEmployee(String name, String doj, String phoneNumber, String aadhaarNumber,
			double monthlySalary) throws EMSCustomException {
		
		//validation
		FullTimeEmployee fte= EMSValidation.ValidateAllInputsFTE(name, doj, phoneNumber, aadhaarNumber, monthlySalary, emplist);
		
		emplist.add(fte);
		return "Added fte";
	}

	//add pte
	@Override
	public String addPartTimeEmployee(String name, String doj, String phoneNumber, String aadhaarNumber,
			double hourlyPaymentAmount) throws EMSCustomException {
		//validation
				PartTimeEmployee pte= EMSValidation.ValidateAllInputsPTE(name, doj, phoneNumber, aadhaarNumber, hourlyPaymentAmount, emplist);
				
				emplist.add(pte);
				return "Added pte";
		
	}

	@Override
	public String DeleteEmployeeById(int id) throws EMSCustomException {
		emplist.removeIf(p->p.getId()==id);
		return null;
	}

	@Override
	public String SearchAadhaarNumber(String aadhaarNumber) throws EMSCustomException {
		emplist.stream().filter(p->p.getAadhaarNumber().equals(aadhaarNumber)).forEach(System.out::println);
		return null;
	}

	//displaying employee
	@Override
	public void displayEmployee() {
		
		emplist.stream().forEach(p->System.out.println(p));
		
	}

	@Override
	public void displayEmployeeSortedByDoj() {
		
		emplist.stream().sorted(Comparator.comparing(p->p.getDoj())).forEach(System.out:: println);
		
	}

}
