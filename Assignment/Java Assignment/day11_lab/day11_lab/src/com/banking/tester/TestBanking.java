
package com.banking.tester;

import java.time.LocalDate;
import java.util.Scanner;

import com.banking.core.AccountType;
import com.banking.custom_exceptions.BankingException;
import com.banking.service.BankingService;
import com.banking.service.BankingServiceImpl;

public class TestBanking {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			BankingService service = new BankingServiceImpl();
			boolean exit = false;

			while (!exit) {
				try {
					System.out.println(
							"1.Open Account\n2.Display\n3.Deposit\n4.Withdraw\n5.Update Phone Number\n6.Transfer fund\n7.Close Account\n8.Sort by Account Number\n9.Sort by Account Type and Balance\n10.sort by customer DOB and Balance\n11.Delete all account less then specified balance\n0.Exit");
					switch (sc.nextInt()) {
					// open account
					case 1: {
//						System.out.println("enter details:");
//						System.out.println("Enter Account number: ");
//						int ac = sc.nextInt();
//						System.out.println("Enter Balance: ");
//						double bal = sc.nextDouble();
//						System.out.println("Enter Name: ");
//						String nm = sc.next();
//						System.out.println("Enter Phone no.: ");
//						String pn = sc.next();
//						System.out.println("Enter Account Type (Saving or Current): ");
//						String acType = sc.next();
//						System.out.println("Enter Date of Birth(yyyy-MM-dd): ");
//						String dob = sc.next();
//						System.out.println("Status" + service.OpenAccount(ac, bal, nm, pn, acType, dob));
						service.OpenAccount(1001, 24500.75, "Rahul", "9876543210", "Saving", "1999-05-12");
						service.OpenAccount(1008, 22000.50, "Ananya", "9823410976", "Saving", "1996-07-27");

						service.OpenAccount(1010, 87500.00, "Neha", "9032145678", "Saving", "1993-09-17");
						service.OpenAccount(1002, 12000.00, "Priya", "9123456789", "Current", "2000-11-23");
						service.OpenAccount(1003, 8000.50, "Amit", "9988776655", "Saving", "1998-03-30");
						service.OpenAccount(1007, 9999.99, "Vikas", "9765432109", "Saving", "2001-12-05");

						service.OpenAccount(1006, 150000.00, "Meera", "9345612789", "Current", "1995-01-09");
						service.OpenAccount(1004, 50000.00, "Sneha", "9012345678", "Current", "1997-08-14");
						service.OpenAccount(1005, 3000.25, "Karan", "9876501234", "Saving", "2002-06-19");

						service.OpenAccount(1009, 4500.75, "Suresh", "9898989898", "Current", "1994-04-02");

						System.out.println();
					}
						break;
					// display all account
					case 2: {
						service.displayAccountDetails();
					}
						break;
					// deposit
					case 3: {
						System.out.println("Enter account number & amount to deposit ");
						int accno = sc.nextInt();
						double amount = sc.nextDouble();
						service.deposit(accno, amount);
						System.out.println("Amount Deposited");
						service.displayAccountSummary(accno);
					}
						break;
					case 4: {
						System.out.println("Enter Account number & Enter Amount to withdraw");
						int accno = sc.nextInt();
						double amount = sc.nextDouble();
						service.withdraw(accno, amount);
						service.displayAccountSummary(accno);
					}
						break;

					case 5: {
						System.out.println("Phone Number Update");
						System.out.println("Enter account number: ");
						int acc = sc.nextInt();
						System.out.println("Enter old phone number: ");
						String oldPh = sc.next();
						System.out.println("Enter new phone number: ");
						String newPh = sc.next();
						service.updatePhoneNumber(acc, oldPh, newPh);
						service.displayAccountSummary(acc);
					}
						break;

					case 6: {
						System.out.println("Transfer Fund");
						System.out.println("Enter source account number");
						int scAcc = sc.nextInt();
						System.out.println("Enter Dest account number");
						int deAcc = sc.nextInt();
						System.out.println("Enter amount");
						double amt = sc.nextDouble();
						service.transferFund(scAcc, deAcc, amt);
						service.displayAccountDetails();
					}
						break;

					case 7: {
						System.out.println("Close your Account");
						System.out.println("Enter account number to close");
						service.closeAccount(sc.nextInt());
					}
						break;

					// sort by account number
					case 8: {
						System.out.println("Sorted by account number");
						service.sortByAccountNumber();
					}
						break;
						
					//sorted as per account type & balance (custom ordering with ano inner class)
					case 9:{
						System.out.println("Sorted by Account Type and Balance");
						service.sortByAccountTypeAndBalance();
					}
					break;
					
					//sorted as per customer DOB & balance (custom ordering with ano inner class)
					case 10:{
						System.out.println("Sorted by DOB and Balances");
						service.sortByDobAndBalance();
					}
					break;
					
					
					//Delete all  accounts with balance < specified balance.
					case 11:{
						System.out.println("Delete all account with specified balance");
						double amt = sc.nextDouble();
						service.deleteAccountLessThanSpecifiedAmount(amt);
					}
					break;
					
					//Exit
					case 0: {
						exit = true;
						System.out.println("Exiting");
					}
						break;
					default:
						throw new BankingException("Invalid Input");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
