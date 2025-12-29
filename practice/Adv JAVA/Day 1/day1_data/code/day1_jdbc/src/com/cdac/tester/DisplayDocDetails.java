package com.cdac.tester;

import java.sql.*;
import static com.cdac.utils.DBUtils.*;

public class DisplayDocDetails {

	public static void main(String[] args) {
		try (// 1. open db connection
				Connection cn = openConnection();
				// 2. Create JDBC Statement - to hold sql .
				Statement st = cn.createStatement();
				// 3. Supply query & execute it.
				ResultSet rst = st.executeQuery("select * FROM doctors")) {
			//4. Process the ResultSet
			/* ResultSet - maintains a cursor internally. 
			 * It's placed before the 1st record | row.
			 *  ResultSet method
			 *  public boolean next() throws SQLException
			 *  - Tries to place the cursor on the nest row.
			 *  - Returns true if row exists , otherwise false.
			 *   id | name              | speciality  | email                    | password  | dob 
			 */
			System.out.println("All docs - ");
			while(rst.next()) {
				System.out.printf("Doc ID %d Name %s Speciality %s DoB %s %n",rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(6));
			}
		} // JVM -rst.close, st.close , cn.close() - release DB resource.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
