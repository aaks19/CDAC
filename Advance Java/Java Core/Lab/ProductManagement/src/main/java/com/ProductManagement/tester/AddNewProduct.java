package com.ProductManagement.tester;

import static com.ProductManagement.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.ProductManagement.dao.ProductDao;
import com.ProductManagement.dao.ProductDaoImpl;
import com.ProductManagement.entities.Category;
import com.ProductManagement.entities.Product;

public class AddNewProduct {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				SessionFactory sf = getFactory()){
			ProductDao productDao = new ProductDaoImpl();
			System.out.println("Enter Product detils- productName, productDescription, "
					+ "Localdate, price, availableQuantity,  category");
			
			Product product = new Product(sc.nextLine(), sc.nextLine(), LocalDate.parse(sc.next()), sc.nextDouble(), sc.nextLong(), Category.valueOf(sc.next().toUpperCase()));
			System.out.println("Product status - "+productDao.addNewProduct(product));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
