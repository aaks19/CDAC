package com.ShoeManagementSystem.services;

import java.util.List;

import com.ShoeManagementSystem.Exception.ShoeManagementException;
import com.ShoeManagementSystem.core.ShoeGallery;
import com.ShoeManagementSystem.core.ShoeType;

public class ShoeManagementValidation {
	public static void checkRatingRange(int rating) throws ShoeManagementException{
		if(rating < 1 || rating > 10) {
			throw new ShoeManagementException("Invalid rating...");
		}
	}
	
	public static void checkName(String name) throws ShoeManagementException{
		if(name.length() < 3 || name.length() > 20) {
			throw new ShoeManagementException("Invalid name...");
		}
	}
	
	public static ShoeType validShoeType(String shoe_Type) throws IllegalArgumentException, ShoeManagementException{
		ShoeType type = ShoeType.valueOf(shoe_Type.toUpperCase());
		return type;
	}
	
	
	public static ShoeGallery validateAll(String name, String brand, int rating, double price, boolean availableInGallery,
			String shoe_type, List<ShoeGallery> shoeList) throws ShoeManagementException{
		checkRatingRange(rating);
		checkName(name);
		ShoeType s_type = validShoeType(shoe_type);
		
		return new ShoeGallery(name,brand,rating,price,availableInGallery, s_type);
	}
}
