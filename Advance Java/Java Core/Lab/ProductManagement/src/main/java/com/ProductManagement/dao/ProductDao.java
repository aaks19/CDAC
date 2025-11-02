package com.ProductManagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.ProductManagement.entities.Category;
import com.ProductManagement.entities.Product;

public interface ProductDao {
	
	//Add a new product
	String addNewProduct(Product newProduct);
	
	//Display all the products
	List<Product> displayProducts();
	
	//Display   id , name , price  of all the products manufactured before specified date and from specific category
	List<Product> displaySpecified(LocalDate mfgDate, Category cat);
	
	//Change Product price
	String changeProductPrice(String prodname, double price);
}
