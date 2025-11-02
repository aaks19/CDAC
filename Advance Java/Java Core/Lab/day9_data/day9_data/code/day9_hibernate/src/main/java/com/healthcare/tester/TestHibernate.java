package com.healthcare.tester;

import static com.healthcare.utils.HibnernateUtils.getFactory;

import org.hibernate.SessionFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory()) {
			System.out.println("Hibernate bootstrapped ! " + sf);
		} // JVM - sf.close() => clean up DBCP

	}

}
