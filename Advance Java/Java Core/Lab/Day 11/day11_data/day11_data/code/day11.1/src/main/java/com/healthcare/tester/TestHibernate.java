package com.healthcare.tester;

import org.hibernate.*;

import com.healthcare.entities.User;

import static com.healthcare.utils.HibernateUtils.getFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory()) {
			System.out.println("hibernate up n running , SF "+factory);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
