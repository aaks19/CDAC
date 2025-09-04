package core.tester;

import core.developers.banking.*;

public class TestBank {
	
	public static void main(String[] args) {
		SavingAccount sa = new SavingAccount(1201254,785458.256,"Akshat","999324");
		CurrentAccount ca = new CurrentAccount(98589654,9985785.256,"Akshat verma","119324");
		
		sa.getAccountSummary();
		double bal = sa.getBalance();
		System.out.println("balance -------> " + bal);
		
		System.out.println("deposite------>");
		sa.setBalance(5000);
		System.out.println("balance -------> " + bal);
		
		System.out.println("Current Account details");
		ca.getAccountSummary();
	}
}
