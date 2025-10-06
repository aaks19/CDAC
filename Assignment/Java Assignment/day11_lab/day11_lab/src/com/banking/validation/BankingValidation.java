package com.banking.validation;

import java.time.LocalDate;
import java.util.List;

import com.banking.core.AccountType;
import com.banking.core.BankAccount;
import com.banking.core.CurrentAccount;
import com.banking.core.SavingAccount;
import com.banking.custom_exceptions.BankingException;

public class BankingValidation {
	
	// add a public static method to check for duplicate account number
	public static void checkForDuplicate(int accountNumber, List<BankAccount> accList) throws BankingException {
		// create new bank account to wrap only account number
		BankAccount a = new BankAccount(accountNumber);
		if (accList.contains(a)) {
			throw new BankingException("Account already exist");
		}
	}

	// add a public static method to validate account type
	public static AccountType validateAccountType(String accType) throws IllegalArgumentException {
		return AccountType.valueOf(accType.toUpperCase());
	}

	public static BankAccount validateAll(int accountNumber, double balance, String customerName, String phoneNumber,
			String acType, String dob, double rateOrLimit, List<BankAccount> accounts) throws BankingException {

		checkForDuplicate(accountNumber, accounts);
		AccountType type = validateAccountType(acType);
		LocalDate dateBirth = LocalDate.parse(dob);
		if (type == AccountType.SAVING) {
			return new SavingAccount(accountNumber, balance, customerName, phoneNumber, type, dateBirth, rateOrLimit);
		} else {
			return new CurrentAccount(accountNumber, balance, customerName, phoneNumber, type, dateBirth, rateOrLimit);
		}
	}
	

}
