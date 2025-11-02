package com.ProductManagement.tester;

import static com.ProductManagement.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.ProductManagement.dao.ProductDao;
import com.ProductManagement.dao.ProductDaoImpl;
import com.ProductManagement.entities.Category;

public class DisplayDetailBeforeSpecifiedDate {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getFactory()) {
			// create user dao instance
			ProductDao productDao = new ProductDaoImpl();
			System.out.println("Enter Category and Date - ");
			LocalDate mfgdate = LocalDate.parse(sc.next());
			Category cat = Category.valueOf(sc.next().toUpperCase());
			productDao.displaySpecified(mfgdate,cat).forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
