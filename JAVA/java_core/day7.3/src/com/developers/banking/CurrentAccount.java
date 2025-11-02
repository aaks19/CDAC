package com.developers.banking;

import java.nio.channels.OverlappingFileLockException;

import custom_exception.InsufficientBalanceException;
import custom_exception.OverdraftLimitZero;

public class CurrentAccount extends BankAccount{

	private double overdraftLimit;
	
	public CurrentAccount(int accountNumber, double balance, String name, String phoneNum,double overdraftLimit) {
		super(accountNumber, balance, name, phoneNum);
		this.overdraftLimit = overdraftLimit;
		
	}

//	public void useOverdraftFacility(double amount)
//	{
//		
//	}
	@Override
	public void withdraw(double amount) throws OverdraftLimitZero 
	{
		if(super.getBalance()-amount > 0)
		{
			super.setBalance(super.getBalance()- amount);
			System.out.println("Amount withdrawn successfull for currentAccount");
		}
		else {
	        double required = amount - super.getBalance();
	        if (required <= overdraftLimit) {
	            overdraftLimit -= required;
	            super.setBalance(0);
	            System.out.println("Amount withdrawn using overdraft facility");
	            System.out.println("Remaining overdraft limit: " + overdraftLimit);
	        } else {
//	            System.out.println("Transaction Declined! Overdraft limit exceeded.");
	        	throw new OverdraftLimitZero("Transaction Declined! Overdraft limit exceeded.");
	        }
	    }
	}
	//deposit
	
		@Override
		public void deposit(double amount)
		{
			super.setBalance(super.getBalance()+ amount);
			
		}
		@Override
		public String toString()
		{
			
			return super.toString()+"\noverdraftLimit= "+ overdraftLimit;
		}
}
