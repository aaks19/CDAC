package com.healthcare.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.healthcare.DbUtils.DBUtils.*;
import com.healthcare.pojos.Doctors;

public class DoctorDaoImpl implements DoctorDao {

	private Connection cn;
	private PreparedStatement pst1, pst2;

	public DoctorDaoImpl() throws SQLException {
		cn = openConnection();

		pst1 = cn.prepareStatement("insert into doctors (name,speciality,email,password,dob) values(?,?,?,?,?)");

		pst2 = cn.prepareStatement("select email from doctors where email = ?");
	}

	@Override
	public String doctorRegister(Doctors newDoctor) throws SQLException {
		boolean flag=false;
		
		pst2.setString(1, newDoctor.getDoc_email());
		try (ResultSet rst = pst2.executeQuery()) {
			if (rst.next()) {
				flag = true;
			}
		}
		if (!flag) {
			pst1.setString(1, newDoctor.getDoc_name());
			pst1.setString(2, newDoctor.getSpeciality());
			pst1.setString(3, newDoctor.getDoc_email());
			pst1.setString(4, newDoctor.getPassword());
			pst1.setDate(5, newDoctor.getDob());
			int rows = pst1.executeUpdate();
			return "Registration Success";
		} else {
			return "duplicate";
		}

	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
			pst1 = null;
		}

		if (pst2 != null) {
			pst2.close();
			pst2 = null;
		}
	}

}
