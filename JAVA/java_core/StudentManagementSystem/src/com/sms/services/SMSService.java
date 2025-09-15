package com.sms.services;

import java.time.LocalDate;

import com.sms.StudentManagementException.StudentManagementException;
import com.sms.core.Course;

public interface SMSService {

	String takeAdmission(String name, String email,  int maks, String courses, String admissionDate ) throws StudentManagementException;
	
	void displayAllStudnets();
	
	//cancle admission
	void cancleAdmission(String email) throws StudentManagementException;
	
	//search student by email
	void searchStudentByEmail(String email) throws StudentManagementException;
}
