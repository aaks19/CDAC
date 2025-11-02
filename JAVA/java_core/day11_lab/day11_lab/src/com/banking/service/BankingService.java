package com.banking.service;

import com.banking.custom_exceptions.BankingException;

public interface BankingService {
	String OpenAccount(int accountNumber, double balance, String customerName, String phoneNumber, String acType,
			String dob) throws BankingException;
	void displayAccountDetails();
	void displayAccountSummary(int accountNumber);
	void deposit(int accountNumber, double amount ) throws BankingException;
	void withdraw(int accountNumber, double amount) throws BankingException;
	void updatePhoneNumber(int accountNumber, String oldPhoneNo, String newPhoneNo) throws BankingException;
	void transferFund(int sourceAccountNumber, int destAccountNumber, double amount) throws BankingException;
	void closeAccount(int accountNumber) throws BankingException;
	
	//sorting
	void sortByAccountNumber();
	
	//sorted as per account type & balance (custom ordering with ano inner class)
	void sortByAccountTypeAndBalance();
	
	//sorted as per customer DOB & balance (custom ordering with ano inner class)
	void sortByDobAndBalance();
	
	void deleteAccountLessThanSpecifiedAmount(double amt);



}
