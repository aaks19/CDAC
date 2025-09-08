package com.tester;

import java.util.Scanner;

import com.developers.banking.BankAccount;
import com.developers.banking.CurrentAccount;
import com.developers.banking.SavingAccount;

import custom_exception.InsufficientBalanceException;
import custom_exception.OverdraftLimitZero;

public class TestBank {

//	Task: Write a TestBanking class , with main() that creates one saving and 
//	one current account, performs deposits and withdrawals, and prints account summary.
//	You can hard code the values (no need to add Scanner today !)

	public static void main(String[] args) throws InsufficientBalanceException,OverdraftLimitZero {
//		Options
//		1. Open Saving account
//		User i/p - accountNumber , balance  , customer name  , phone number , interest rate
//
//		2. Open Current account
//		User i/p - accountNumber , balance  , customer name  , phone number ,overdraftLimit 
//
//		3. Display account summary
//		User i/p - account number
//
//		4. Deposit
//		User i/p - account number , amount
//
//		5. Withdraw
//		User i/p - account number , amount
		Scanner sc = new Scanner(System.in);
		boolean flag = false;

		BankAccount[] bank = new BankAccount[100];
		int counter = 0;
		while (!flag) {
			System.out.println(
					"Enter Choice :\n1. Open Saving account \n2. Open Current account \n3. Display account summary\n4. Deposit\n5. Withdraw\n6. Exit ");
			switch (sc.nextInt()) {
			case 1:
				if (counter < bank.length) {
					System.out.println(
							"\nEnter accountNumber , balance  , customer name  , phone number");
					bank[counter] = new SavingAccount(sc.nextInt(), sc.nextDouble(), sc.next(), sc.next());
					counter++;
					System.out.println("Successfull Creation :SavingAccount");
				}
				break;
			case 2:
				if (counter < bank.length) {
					System.out.println(
							"\nEnter accountNumber , balance  , customer name  , phone number , overdraftLimit");
					bank[counter] = new CurrentAccount(sc.nextInt(), sc.nextDouble(), sc.next(), sc.next(),sc.nextDouble());
					counter++;
					System.out.println("Successfull Creation :Current Account");
				}
				break;
			case 3:
				System.out.println("---------Account Summary-----------");
				for (BankAccount b : bank) {
					if (b != null) {

						System.out.println(b);
						System.out.println("--------------------");
					}

				}

				break;
			case 4:
				System.out.println("Enter Account No.");
				int acno = sc.nextInt();
				boolean found = false;

				for (int i = 0; i < counter; i++) {

					BankAccount b = bank[i];
					if (b.getAccountNumber() == acno) {
						found = true;
						if (b instanceof SavingAccount) {
							System.out.println("Enter ammount to deposite:");
							((SavingAccount) b).deposit(sc.nextDouble());
							System.out.println("Amount Deposited into your saving account");
							System.out.println(b.toString());
						} else if (b instanceof CurrentAccount) {
							System.out.println("Enter ammount to deposite:");
							((CurrentAccount) b).deposit(sc.nextDouble());
							System.out.println("Amount Deposited into your Current account");
							System.out.println(b.toString());
						} else {
							System.out.println("invalid account type!");
						}
						break;
					}
				}
				if (!found) {
					System.out.println("Account no. not found!!");
				}

				break;

			case 5:
				System.out.println("Enter Account number: ");
				acno = sc.nextInt();
				found = false;
				for(int i=0;i<counter;i++) {
					BankAccount b = bank[i];
					if(b.getAccountNumber() == acno) {
						found = true;
						
						if(b instanceof SavingAccount) {
							System.out.println("Enter ammount to withdraw");
							((SavingAccount)b).withdraw(sc.nextDouble());
							System.out.println("After withdraw account summary");
							System.out.println(b.toString());
						}else if(b instanceof CurrentAccount) {
							System.out.println("Enter ammount to withdraw");
							((CurrentAccount)b).withdraw(sc.nextDouble());
							System.out.println("After withdraw account summary");
							System.out.println(b.toString());
						}else {
							System.out.println("invalid account type!");
						}
					}
				}
				if (!found) {
					System.out.println("Account no. not found!!");
				}

				break;
			case 6:
				flag = true;
				break;
			}
		}

		sc.close();
	}
}
