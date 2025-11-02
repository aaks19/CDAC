package com.dbutils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {

	private static String url = "jdbc:mysql://localhost:3306/iacsd_25";
	private static String userName = "root";
	private static String password = "root123";
	private static Connection connection;
	public static Connection openConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(url, userName, password);
		return connection;
		
	}
	
}
