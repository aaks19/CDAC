package com.healthcare.tester;
/*
 * 1.	Image Handling
	 1.1 Save user image in DB		
i/p - user id , image file name(path)
o/p – message

 */
import static com.healthcare.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;

public class SaveUserImage {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			// create user dao instance
			UserDao userDao = new UserDaoImpl();
			System.out.println("Enter user id , image file name(path)");
			Long userId=sc.nextLong();
			sc.nextLine();
			String path=sc.nextLine();
			System.out.println(userDao.saveImage(userId,path));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
