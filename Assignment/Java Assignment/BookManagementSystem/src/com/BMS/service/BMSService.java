package com.BMS.service;

import com.BMS.Exception.BMSException;

public interface BMSService {
	String addBook(String title, String author, String isbn, double price, double rating) throws BMSException;
	
	void updatePrice(String isbn, double price) throws BMSException;
	
	void searchByTitle(String title) throws BMSException;
	
	void displayAllBooks();
	
	void displayBooksSorted();
}
