package com.cdac.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	private static String url="jdbc:mysql://localhost:3306/iacsd_25";
	private static String userName="root";
	private static String password="root";
	
//add public static method to get connection to DB
	public static Connection openConnection() throws ClassNotFoundException,SQLException{
		//load & register JDBC drive - CCEE
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection(url, userName, password);
		return connection;
	}
}
