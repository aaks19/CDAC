package com.healthcare.tester;

import java.util.Scanner;

import com.healthcare.dao.PatientDao;
import com.healthcare.dao.PatientDaoImpl;

public class DeletePatient {

	public static void main(String[] args) {
		
		
		try(Scanner sc = new Scanner(System.in)){
			PatientDao dao = new PatientDaoImpl();
			System.out.println("Enter id to delete: ");
			dao.deletePatient(sc.nextInt());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
