package com.healthcare.tester;

import java.util.Scanner;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;

public class PatientSignIn {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			PatientDao dao = new PatientDaoImpl();
			
			System.out.println("Enter Email: ");
			System.out.println("Enter Password: ");
			dao.signIn(sc.next(), sc.next());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
