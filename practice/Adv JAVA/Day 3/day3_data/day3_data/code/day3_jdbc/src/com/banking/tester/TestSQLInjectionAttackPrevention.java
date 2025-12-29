package com.banking.tester;

import static com.banking.utils.DBUtils.*;

import java.sql.*;
import java.util.Scanner;

public class TestSQLInjectionAttackPrevention {

	public static void main(String[] args) {
		System.out.println("Enter Doctor id");
		try (Scanner sc = new Scanner(System.in)) {
			Connection cn =openConnection();
			 
		// create JDBC PreparedStatement from connection
				PreparedStatement pst = 
						cn.prepareStatement("select * from doctors where id=?"); 

			String id = sc.nextLine();
			// set IN params
			pst.setInt(1, Integer.parseInt(id));
			ResultSet rst = pst.executeQuery();
			System.out.println("Doctor Details - ");
			while (rst.next()) {
				System.out.printf("ID %d Name %s  Speciality  %s  %n", rst.getInt(1), rst.getString(2),
						rst.getString(3));
			}
			rst.close();
			pst.close();
			closeConnection();

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");

	}

}
