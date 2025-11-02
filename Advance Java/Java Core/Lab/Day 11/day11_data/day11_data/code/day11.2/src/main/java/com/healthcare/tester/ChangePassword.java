package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;

public class ChangePassword {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create user dao instance
			UserDao userDao = new UserDaoImpl();
			System.out.println("Enter User's email , password & new password");
			System.out.println(userDao.changePassword(sc.next(),sc.next(),sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
