package com.banking.tester;

import static com.banking.utils.DBUtils.*;

import java.sql.*;
import java.sql.Statement;
import java.util.Scanner;

public class TestSQLInjectionAttack {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Doctor id");
			String id = sc.nextLine();
			Connection cn = openConnection();

			// create JDBC statement from connection
			Statement stmt = cn.createStatement();
			// execute select query to produce a result set
			ResultSet rst = stmt.executeQuery("select * from doctors where id=" + id);
			System.out.println("Doctor Details - ");
			while (rst.next()) {
				System.out.printf("ID %d Name %s  Speciality  %s  %n", rst.getInt(1), rst.getString(2),
						rst.getString(3));
			}
			stmt.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("main over...");
	}

}
