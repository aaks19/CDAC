package com.developers.banking;

public class SavingAccount extends BankAccount{

	private static double interestRate = 7;
	
	//constructor
	 public SavingAccount(int accountNumber, double balance, String name, String phoneNum)
	{
		 super(accountNumber, balance, name, phoneNum);		 
	}
	public void applyInterest()
	{
		System.out.println("Applying interst");
		double applyInterest=super.getBalance()+(super.getBalance()* (interestRate/100));
		super.setBalance(applyInterest);
		System.out.println("Current Banlance After Interest:"+super.getBalance());
	}
	
	@Override
	public void withdraw(double amount)
	{
		if(super.getBalance()-amount>0)
		{
			super.setBalance(super.getBalance()- amount);
			System.out.println("Amount withdrawn successfull");
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
		
		return super.toString()+"\ninterestRate = "+interestRate;
	}
		
	
}
