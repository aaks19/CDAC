package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public class DisplayUserDetailsById {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getFactory()) {
			// create user dao instance
			UserDao userDao = new UserDaoImpl();
			System.out.println("Enter User id");
			System.out.println(userDao.getUserDetailsById(sc.nextLong()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
