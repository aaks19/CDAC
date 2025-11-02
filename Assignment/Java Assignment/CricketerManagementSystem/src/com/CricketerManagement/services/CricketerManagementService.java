package com.CricketerManagement.services;

import com.CricketerManagement.core.Cricketer;
import com.CricketerManagement.exception.CricketManagementSystemException;

public interface CricketerManagementService {

	/*
	 * 1.Accept minimum 5 Cricketers in the collection.
	 * 
	 * 2.Modify Cricketer's rating
	 * 
	 * 3.Search Cricketer by name
	 * 
	 * 4. Display all Cricketers added in collection.
	 * 
	 * 5.Display All Cricketers in sorted form by rating.
	 */
	
	
	String addCricketer(String name, int age, String email_id, String phone, double rating) throws CricketManagementSystemException;
	
	void modifyRating(String email_id,double rating) throws CricketManagementSystemException;
	
	void searchCricketer(String name) throws CricketManagementSystemException;
	
	void displayAllCricketer() ;
	
	void DisplaySortedByRating();
}
