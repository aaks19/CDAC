package com.CricketerManagement.services;

import java.util.List;
import java.util.Map;

import com.CricketerManagement.core.Cricketer;
import com.CricketerManagement.exception.CricketManagementSystemException;

public class CricketerValidation {
	
	
	public static Cricketer validateAll(String name, int age, String email_id, String phone, double rating, Map<Integer,Cricketer> cricketerMap) throws CricketManagementSystemException{
		checkEmail(email_id);
		checkDuplicate(email_id, cricketerMap);
		
		return new Cricketer(name, age, email_id, phone, rating);
	}

	public static void checkEmail(String email_id) throws CricketManagementSystemException{
		String regex = "^[a-z][a-z0-9._-]*@[a-z]+\\.(com|org|net)$";
		
		if(!email_id.matches(regex)) {
			throw new CricketManagementSystemException("Invalid Email-Id...");
		}
	}
	
	
	public static void checkDuplicate(String email_id, Map<Integer,Cricketer> cricketerMap) throws CricketManagementSystemException{
		Cricketer c = new Cricketer(email_id);
		
//		if(cricketersList.contains(c)) {
//			throw new CricketManagementSystemException("Email already exist...");
//		}
		
		boolean duplicate = cricketerMap.values()
										.stream()
										.anyMatch(p->p.getEmail_id().equals(email_id));
		
		if(duplicate) {
			throw new CricketManagementSystemException("Email is Already used...");
		}
		
	}
}
