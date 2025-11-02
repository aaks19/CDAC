package com.banking.tester;

import java.util.Scanner;

import com.banking.dao.BankAccountDao;
import com.banking.dao.BankAccountDaoImpl;

public class TestStoredProcedure {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)) {
			//dao instance
			BankAccountDao dao=new BankAccountDaoImpl();
			System.out.println("Enter src , dest acct nos & amount");
			System.out.println(dao.transferFunds(sc.nextInt(), sc.nextInt(), sc.nextDouble()));
			dao.cleanUp();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
