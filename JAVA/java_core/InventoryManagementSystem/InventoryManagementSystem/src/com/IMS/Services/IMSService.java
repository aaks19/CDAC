package com.IMS.Services;

import java.time.LocalDate;

import com.IMS.Exception.IMSException;
import com.IMS.core.Category;

public interface IMSService {

	void addPerishableItem(String name, String category, double price, int quantity, String expiryDate)
			throws IMSException;

	void addNonPerishableItem(String name, String category, double price, int quantity, int warrentrPeriod)
			throws IMSException;

	void deleteItemByCode(int id) throws IMSException;

	void searchItem(String name) throws IMSException;

	void displayAllItems();

	void displayItemSortedByPrice();

	void displayExpiredItem();
}
