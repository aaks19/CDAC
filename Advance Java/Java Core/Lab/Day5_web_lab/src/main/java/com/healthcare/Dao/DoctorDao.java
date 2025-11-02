package com.healthcare.Dao;

import java.sql.SQLException;

import com.healthcare.pojos.Doctors;

public interface DoctorDao {

	String doctorRegister(Doctors newDoctor) throws SQLException;
	
	void cleanUp() throws SQLException;
}
