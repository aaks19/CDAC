package com.healthcare.dao;

import java.sql.SQLException;

import com.healthcare.pojos.Patient;

public interface PatientDao {
	Patient signIn(String email, String password) throws SQLException;

	void cleanUp() throws SQLException;

}
