package com.banking.tester;

import java.util.ArrayList;

import com.banking.core.BankAccount;
import com.banking.custom_exceptions.BankingException;
import com.banking.validation.BankingValidation;

public class test1 {
	public static void main(String[] args) throws BankingException{
		ArrayList<BankAccount> list = new ArrayList<>();
		list.add(new BankAccount(101, 10000, "aks", null, null, null));
		list.add(new BankAccount(102, 20000, "akss", null, null, null));
		list.add(new BankAccount(103, 30000, "akshat", null, null, null));
		
		for(BankAccount bank:list) {
			System.out.println(bank);
		}
		
		//call Validation
		BankingValidation.checkForDuplicate(102, list);
	}
}
