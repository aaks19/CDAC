package com.cdac.tester;
/*
 * Fetch id, name , speciality,dob  of all  doctors born after specified date.
 */
import java.sql.*;
import java.util.Scanner;
import static com.cdac.utils.DBUtils.*;
public class DisplayDoctorsDetailsByDob {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				//1. get cn
				Connection cn=openConnection();
				//2. Create PST from cn
				PreparedStatement pst=cn.prepareStatement("select id,name, speciality,dob from doctors where dob > ?");
				
				) {
			//3. Accept n parse Date
			System.out.println("Enter date - yyyy-[M]M-[d]d");
			Date date=Date.valueOf(sc.next());
			//4 . set Date (set IN param)
			pst.setDate(1, date);
			//5. execute query -> RST -> process it 
			try(ResultSet rst=pst.executeQuery()){
				while(rst.next())
				{
					System.out.printf("id %d  name %s speciality %s dob %s %n",
							rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4));
				}
			}//JVM - rst.close
			
			
		} //pst.close , cn.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
