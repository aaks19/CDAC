package com.banking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.banking.core.AccountType;
import com.banking.core.BankAccount;
import com.banking.custom_exceptions.BankingException;
import com.banking.validation.BankingValidation;

public class BankingServiceImpl implements BankingService {

	ArrayList<BankAccount> accounts = new ArrayList<>();

	@Override
	public String OpenAccount(int accountNumber, double balance, String customerName, String phoneNumber, String acType,
			String dob) throws BankingException {

		AccountType acctype = AccountType.valueOf(acType.toUpperCase());
		LocalDate dobirth = LocalDate.parse(dob);
		double rateOrLimit = (acctype == AccountType.SAVING) ? 7.0 : 10000;

		BankAccount b = BankingValidation.validateAll(accountNumber, balance, customerName, phoneNumber, acType, dob,
				rateOrLimit, accounts);
		accounts.add(b);
		return "Success";
	}

	@Override
	public void displayAccountDetails() {
		
		for (BankAccount details : accounts) {
			System.out.println(details);
		}
	}

	@Override
	public void deposit(int accountNumber, double amount) throws BankingException {
		if(amount<=0) {
			throw new BankingException("Amount is less");
		}

		for(BankAccount b : accounts) {
			if(b.getAccountNumber() == accountNumber) {
				b.deposit(amount);
				
				return;
			}
		}
		throw new BankingException("Account not found");
	}

	@Override
	public void withdraw(int accountNumber, double amount) throws BankingException {
		
//		BankAccount ac = accounts.get(accountNumber);
		for(BankAccount b : accounts) {
			if(b.getAccountNumber() == accountNumber) {
				b.withdraw(amount);
				System.out.println("Withdrawn Successfully from Your "+b.getAcType()+" Account no.:"+b.getAccountNumber());
				return;
			}	
		}
		throw new BankingException("Account not found!!!");
	}
	
	
	@Override
	public void displayAccountSummary(int accountNumber) {
		for(BankAccount b : accounts) {
			if(accountNumber == b.getAccountNumber()) {
				System.out.println(b);
			}
		}
	}
	
	//Update phone number.
	@Override
	public void updatePhoneNumber(int accountNumber,String oldPhoneNo, String newPhoneNo) throws BankingException{
		for(BankAccount b : accounts ) {
			if(accountNumber == b.getAccountNumber()) {
				if(b.getPhoneNumber().equals(oldPhoneNo)) {
					b.setPhoneNumber(newPhoneNo);
					System.out.println("Phone no updated");
				}else {
					throw new BankingException("Phone no not match");
				}
				return;
			}
		}
		throw new BankingException("Account number not found");
	}
	
	
	//transfer fund from one account to another account
	public void transferFund(int sourceAccountNumber, int destAccountNumber, double amount) throws BankingException{
		BankAccount source = null;
		BankAccount dest = null;
		for(BankAccount b : accounts) {
			if(sourceAccountNumber == b.getAccountNumber()) {
				source = b;
			}
			if(destAccountNumber == b.getAccountNumber()) {
				dest = b;
			}
		}
		source.withdraw(amount);
		dest.deposit(amount);
		System.out.println(amount+" is transfered from account no "+ source.getAccountNumber()+" to account no "+dest.getAccountNumber());
	}
	 
	//Close account
	public void closeAccount(int accountNumber) throws BankingException{
		for(BankAccount b : accounts) {
			if(accountNumber == b.getAccountNumber()) {
				accounts.remove(b);
				System.out.println("Account Number "+b.getAccountNumber()+" is Closed...");
				return;
			}
		}
		throw new BankingException("Account number not found!!!");
		
	}
	
	
	//sort by account number
	@Override
	public void sortByAccountNumber() {
		Collections.sort(accounts);
	}
	
	//sorted as per account type & balance (custom ordering with ano inner class)

	@Override
	public void sortByAccountTypeAndBalance() {
		//use sort method from Collections
		Collections.sort(accounts, new Comparator<BankAccount>() {
			@Override
			public int compare(BankAccount ac1, BankAccount ac2) {
				int ret = ac1.getAcType().compareTo(ac2.getAcType());
				if(ret == 0) {
					return ((Double)ac1.getBalance()).compareTo(ac2.getBalance());
				}
				return ret;
			}
		});
	}
	
	//sorted as per customer DOB & balance (custom ordering with ano inner class)
	@Override
	public void sortByDobAndBalance() {
		Collections.sort(accounts, new Comparator<BankAccount>(){
			@Override
			public int compare(BankAccount ac1, BankAccount ac2) {
				int ret = ac1.getDob().compareTo(ac2.getDob());
				if(ret == 0) {
					return ((Double)ac1.getBalance()).compareTo(ac2.getBalance());
				}
				return ret;
			}
		});
	}
	
	
	@Override
	public void deleteAccountLessThanSpecifiedAmount(double amt) {
		Iterator<BankAccount> itr = accounts.iterator();
		while(itr.hasNext()) {
			if(itr.next().getBalance() < amt) {
				itr.remove();
			}
		}
		System.out.println("successfully removed those account!!!");
	}

}
