package com.banking.core;

import java.time.LocalDate;

import com.banking.custom_exceptions.BankingException;

/*
 * accountNumber (int), balance (double) , customer name (String) 
 * , phone number (String)

 */
public class BankAccount implements Comparable<BankAccount> 
{
	private int accountNumber;
	private double balance;
	private String customerName;
	private String phoneNumber;
	// add account type : enum & dob - customer : LocalDate here
	private AccountType acType;
	private LocalDate dob;

	
	

public BankAccount(int accountNumber, double balance, String customerName, String phoneNumber, AccountType acType,
			LocalDate dob) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.acType = acType;
		this.dob = dob;
	}

	public BankAccount(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public BankAccount(AccountType acType) {
		this.acType = acType;
	}


	public void withdraw(double amount) throws BankingException {
		if (amount > balance)
			throw new BankingException("Withdraw Failed - Insufficient Funds !!!!");
		balance -= amount;
		System.out.println("Withdrawn  " + amount + ". New balance = " + balance);
	}

	public void deposit(double amount) throws BankingException {
		if (amount <= 0)
			throw new BankingException("Deposit Failed - Invalid deposit amount");
		balance += amount;
	}


	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", balance=" + balance + ", customerName=" + customerName
				+ ", phoneNumber=" + phoneNumber + ", acType=" + acType + ", dob=" + dob + "]";
	}



	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
		
	public AccountType getAcType() {
		return acType;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("in account equals");
		if(obj instanceof BankAccount) {
			return this.accountNumber == ((BankAccount)obj).accountNumber;
		}   
		return false;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public LocalDate getDob() {
		return dob;
	}

	@Override
	public int compareTo(BankAccount anotherAccount) {
		return ((Integer)this.accountNumber).compareTo(anotherAccount.accountNumber);
	}

}
