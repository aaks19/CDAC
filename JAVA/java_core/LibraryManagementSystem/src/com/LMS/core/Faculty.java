package com.LMS.core;

import java.time.LocalDate;

public class Faculty extends Members{
	private String departmentName;
	private String designation;
	public Faculty(String name, LocalDate dateOfMembership, String phoneNumber, String aadhaarCard,
			String departmentName, String designation) {
		super(name, dateOfMembership, phoneNumber, aadhaarCard);
		this.departmentName = departmentName;
		this.designation = designation;
	}
	
	
	
	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	@Override
	public String toString() {
		return super.toString()+" departmentName=" + departmentName + ", designation=" + designation + "]";
	}
	
	
}
