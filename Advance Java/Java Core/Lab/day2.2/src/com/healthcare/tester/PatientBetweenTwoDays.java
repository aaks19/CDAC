package com.healthcare.tester;

import java.util.Scanner;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;

public class PatientBetweenTwoDays {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			PatientDao dao = new PatientDaoImpl();
			
			System.out.println("Enter start date: ");
			System.out.println("Enter End date: ");
//			System.out.println(dao.displayPatientBetweenDate(sc.next(), sc.next()));
			dao.displayPatientBetweenDate(sc.next(), sc.next()).forEach(System.out::println);;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
