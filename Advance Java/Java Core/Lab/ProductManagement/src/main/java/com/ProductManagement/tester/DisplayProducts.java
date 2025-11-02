package com.ProductManagement.tester;

import static com.ProductManagement.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.ProductManagement.dao.ProductDao;
import com.ProductManagement.dao.ProductDaoImpl;

public class DisplayProducts {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getFactory()) {
			// create user dao instance
			ProductDao productDao = new ProductDaoImpl();
			System.out.println("All users - ");
			productDao.displayProducts().forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
