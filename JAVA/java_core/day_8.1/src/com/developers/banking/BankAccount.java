package com.developers.banking;
//import java.lang.Object.*;

import custom_exception.InsufficientBalanceException;
import custom_exception.OverdraftLimitZero;

public class BankAccount {

//	Q1. Basic Inheritance
//
//	Create a class BankAccount with:
//
//	Fields: accountNumber (int), balance (double) , customer name (String) , phone number (String)
//
//	Add suitable constructor
	
	private int  accountNumber;
	private double balance;
	private String name;
	private String phoneNum;
	
	
	public BankAccount(int accountNumber, double balance, String name, String phoneNum) {
		//super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
		this.phoneNum = phoneNum;
	}

//	deposit(double amount)
//
//	withdraw(double amount) (should not allow negative balance by default)
//
//	getAccountSummary() - returns complete details of the account , in string format
//
//	getBalance() - returns account balance.
	
	

	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

	//getter method for account
	public int getAccountNumber() {
		return accountNumber;
	}


	public String getName() {
		return name;
	}


	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void deposit(double amount)
	{
		this.balance= this.balance+ amount;
		
	}
	
	public void withdraw(double amount) throws InsufficientBalanceException,OverdraftLimitZero
	{
		if(this.balance>0)
		{
		this.balance= this.balance-amount;
		System.out.println("withdraw successfull");
		}
		else
		{
			//System.out.println("not have enough balance");
			throw new InsufficientBalanceException("Insufficient Balance in your Savings Account");
		}
	}
	//account summary..............
	//assignment day4
//	public void accountSummary()
//	{
//		System.out.println("account Number: "+accountNumber+"Balance: "+balance+"CustomerName: "+name+"PhoneNo: "+phoneNum);
//	}
	
	//assignment day5 using toString() method -> to display accountSummary()
	
	@Override
	public boolean equals(Object anotherAcc)
	{
		if(anotherAcc instanceof BankAccount) {
			return this.accountNumber == ((BankAccount) anotherAcc).accountNumber;
		}
		return false;
	}
	
	
	@Override
	public String toString()
	{
		
		return "account Number: "+accountNumber+"\nBalance: "+balance+"\nCustomerName: "+name+"\nPhoneNo: "+phoneNum;
	}
	

}
