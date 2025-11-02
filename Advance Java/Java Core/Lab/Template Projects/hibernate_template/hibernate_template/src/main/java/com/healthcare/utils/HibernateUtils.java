package com.healthcare.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	private static SessionFactory factory;
	
	static {
		System.out.println("in static initialization block");
		factory = new Configuration()
				.configure() // hibernate loads the properties and mapping from the configuration file in the 
				.buildSessionFactory();
	}

	public static SessionFactory getFactory() {
		return factory;
	}


//	public static SessionFactory getFactory() {
//		return factory;
//	}
	
	
}
