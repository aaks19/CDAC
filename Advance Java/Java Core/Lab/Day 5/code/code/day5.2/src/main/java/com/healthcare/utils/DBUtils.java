package com.healthcare.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	
	
//add public static method to get connection to DB
	public static Connection openConnection(String url,String userName,String password) throws SQLException{
	
		connection=DriverManager.getConnection(url, userName, password);
		return connection;
	}
	//close connection
	public static void closeConnection() throws SQLException{
		if(connection != null)
			connection.close();
	}
}
