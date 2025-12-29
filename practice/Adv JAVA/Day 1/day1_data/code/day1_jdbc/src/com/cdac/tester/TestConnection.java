package com.cdac.tester;

import java.sql.*;
import static com.cdac.utils.DBUtils.*;

public class TestConnection {

	public static void main(String[] args) {
		try (Connection cn = openConnection()) {
			System.out.println("Connected to DB " +cn);
		} //JVM - cn.close() - release DB resource.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
