package com.healthcare.dao;

import java.sql.SQLException;
import java.util.List;

import com.healthcare.pojos.Patient;

public interface PatientDao {
	List<Patient> signIn(String email, String password) throws SQLException;
	
	List<Patient> displayPatientBetweenDate(String startDate, String enddate) throws SQLException;
	
	String deletePatient(int id) throws SQLException;
	
	
	void cleanUp() throws SQLException;
}
