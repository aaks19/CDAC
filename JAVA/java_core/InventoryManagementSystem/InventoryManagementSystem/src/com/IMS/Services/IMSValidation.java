package com.IMS.Services;

import java.time.LocalDate;
import java.util.Map;

import com.IMS.Exception.IMSException;
import com.IMS.core.Category;
import com.IMS.core.Inventory;
import com.IMS.core.NonPerishable;
import com.IMS.core.Perishable;

public class IMSValidation {

	public static void checkItemName(String name) throws IMSException {
		if (name.isEmpty()) {
			throw new IMSException("item name can't be empty");
		}
	}

	public static void checkPrice(double price) throws IMSException {
		if (price < 0) {
			throw new IMSException("Price can't be negative");
		}
	}

	public static void chechQuantity(int qty) throws IMSException {
		if (qty < 0) {
			throw new IMSException("Quantity can't be negative");
		}
	}

	public static LocalDate checkExpiryDate(String expiryDate) throws IllegalArgumentException, IMSException {
		LocalDate expDate = LocalDate.parse(expiryDate);

		if (!expDate.isAfter(LocalDate.now())) {
			throw new IMSException("Expiry date must be in future");
		}
		return expDate;
	}

	public static void checkWarranty(int warranty) throws IMSException {
		if (warranty < 0 || warranty > 60) {
			throw new IMSException("Invalid warrenty");
		}
	}

//	public static Inventory validateALl(String name, String category, double price, int quantity, String expiryDate,
//			int warrentrPeriod, List<Inventory> inventoryList) throws IMSException {
//		Category cat = Category.valueOf(category.toUpperCase());
//		LocalDate expDate = checkExpiryDate(expiryDate);
//		
//		if(cat.equals(Category.PERISHABLE)) {
//			return new Perishable(name, cat, price, quantity, expDate);
//		}
//		else{
//			return new NonPerishable(name, cat, price, quantity, warrentrPeriod);
//		}
//	}
	
	
	//using hashmap
	public static Inventory validateALl(String name, String category, double price, int quantity, String expiryDate,
			int warrentrPeriod, Map<Integer,Inventory> inventoryMap) throws IMSException {
		Category cat = Category.valueOf(category.toUpperCase());
		
		if(cat.equals(Category.PERISHABLE)) {
			LocalDate expDate = checkExpiryDate(expiryDate);
			return new Perishable(name, cat, price, quantity, expDate);
		}
		else if(cat.equals(Category.NONPERISHABLE)){
			return new NonPerishable(name, cat, price, quantity, warrentrPeriod);
		}
		else {
			return null;
		}
	}
}
