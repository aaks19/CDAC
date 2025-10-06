package com.LMS.service;

import java.time.LocalDate;

import com.LMS.Exception.LMSException;

public interface LMSService {
	void addStudent(String name, String dateOfMembership, String phoneNumber, String aadhaarCard, String courseName,
			int yearOfStudy) throws LMSException;
	
	void addFaculty(String name, String dateOfMembership, String phoneNumber, String aadhaarCard,
			String departmentName, String designation) throws LMSException;
	
	void addBook(String title, String author, double price, boolean isAvailable) throws LMSException;
	
	void issueBookToMember(int bookId, int memberId) throws LMSException;
	
	void returnBook(int bookId) throws LMSException;
	
	void deleteMember(int member_id) throws LMSException;
	
	void searchMemberByAadhaar(String aadhaaeNumber) throws LMSException;
	
	void displayAllMember();
	
	void displayAllBookSortedByTitle();
}
