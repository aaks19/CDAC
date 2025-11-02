package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;

public class DisplaySelectedUserDetailsByRole {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create user dao instance
			UserDao userDao = new UserDaoImpl();
			System.out.println("Enter role ");
			UserRole role = UserRole.valueOf(sc.next().toUpperCase());
			System.out.println("Selected Details (firstName , lastName , dob) of users of role " + role);
			userDao.getSelectedDetailsByRole(role)
			.forEach(u -> 
			System.out.println(u.getFirstName()+" "+u.getLastName()+" "+u.getDob()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
