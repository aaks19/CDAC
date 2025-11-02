package com.voting.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutils {
	private static Connection connections;
	private static String url = "jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
	private static String username = "root";
	private static String password = "aks123";
	
	public static Connection openConnection() throws SQLException{
		connections = DriverManager.getConnection(url, username, password);
		return connections;
	}
	
	public static void closeConnection() throws SQLException{
		if(connections!=null) {
			connections.close();
		}
	}
}
