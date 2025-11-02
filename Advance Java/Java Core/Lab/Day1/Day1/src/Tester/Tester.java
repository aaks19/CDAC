package Tester;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dbutils.DButils;

public class Tester {

	public static void main(String[] args) {
		
		
		try(Connection cn = DButils.openConnection();
				
				//2)step 2
				Statement st = cn.createStatement();
				//3)step 3
				ResultSet rst = st.executeQuery("select * from patients");
				
				)
		{
			//1)step 1 
			System.out.println("connected to MySql "+ cn);
			
			//4)step 4
			while(rst.next())
			{
				//id | name          | email                    | password | phone      | dob
				System.out.printf("patient id : %d, patient Name: %s, patient phoneno: %s, patient dob :%s %n",rst.getInt(1),rst.getString(2),rst.getString(5),rst.getString(6));
			}
//			//optional
//			rst.close();
//			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
