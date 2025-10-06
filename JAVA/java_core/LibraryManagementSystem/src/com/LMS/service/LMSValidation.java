package com.LMS.service;

import java.time.LocalDate;
import java.util.List;

import com.LMS.Exception.LMSException;
import com.LMS.core.Faculty;
import com.LMS.core.Members;
import com.LMS.core.Student;

public class LMSValidation {

//	Phone number must be numeric and exactly 10 digits
	public static void checkPhoneNumber(String phoneNumber) throws LMSException{
		if(!phoneNumber.matches("^[0-9]{10}")) {
			throw new LMSException("invalid phone number");
		}
	}
	
//	Aadhaar number must be numeric and exactly 12 digits
	public static void checkAadhaarNumber(String aadhaarNumber) throws LMSException{
		if(!aadhaarNumber.matches("^[1-9][0-9]{11}")) {
			throw new LMSException("invalid aadhaar number");
		}
	}
	
	//duplicate aadhaar number
	public static void duplicateAadhaar(String aadhaarNumber, List<Members> memberList) throws LMSException{
		boolean exist = memberList.stream()
								  .anyMatch(s->s.getAadhaarCard().equals(aadhaarNumber));
		
		if(exist) {
			throw new LMSException("Duplicate aadhaar number");
		}
	}
	
	public static LocalDate validDateMembership(String dateOfMembership) throws IllegalArgumentException{
		LocalDate d = LocalDate.parse(dateOfMembership);
		
		return d;
	}
	
	
	public static Student validateAllStudent(String name, String dateOfMembership, String phoneNumber, String aadhaarCard, String courseName,
			int yearOfStudy,List<Members> memberList) throws LMSException{
		checkPhoneNumber(phoneNumber);
		checkAadhaarNumber(aadhaarCard);
		duplicateAadhaar(aadhaarCard, memberList);
		LocalDate dateMembership = validDateMembership(dateOfMembership);
		
		return new Student(name, dateMembership, phoneNumber, aadhaarCard, courseName, yearOfStudy);
	}
	
	public static Faculty validateAllFaculty(String name, String dateOfMembership, String phoneNumber, String aadhaarCard,
			String departmentName, String designation,List<Members> memberList) throws LMSException{
		checkPhoneNumber(phoneNumber);
		checkAadhaarNumber(aadhaarCard);
		duplicateAadhaar(aadhaarCard, memberList);
		LocalDate dateMembership = validDateMembership(dateOfMembership);
		
		return new Faculty(name, dateMembership, phoneNumber, aadhaarCard, departmentName, designation);
	}
	
}
