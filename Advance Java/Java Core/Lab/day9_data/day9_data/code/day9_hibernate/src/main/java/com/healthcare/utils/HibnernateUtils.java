package com.healthcare.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibnernateUtils {
	private static SessionFactory factory;
	static {
		System.out.println("in static block");
		factory = new Configuration() // empty
				.configure() // populated config
				.buildSessionFactory();// build singleton instance of SF
	}

	public static SessionFactory getFactory() {
		return factory;
	}

}
