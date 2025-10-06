package com.ShoeManagementSystem.services;

import com.ShoeManagementSystem.Exception.ShoeManagementException;
import com.ShoeManagementSystem.core.ShoeType;

public interface ShoeManagementService {
	String addNewShoe(String name, String brand, int rating, double price, boolean availableInGallery,
			String shoe_type) throws ShoeManagementException;
	
	void displayAllShoe();
	
	void displayShoeSortedById();
	
	void searchMostExpensiveShoe() throws ShoeManagementException;
	
	void removeShoe() throws ShoeManagementException;
	
	void updatePrice(String brand,double prices) throws ShoeManagementException;
	
	void sortShoeByPriceDescending();
	
	
}
