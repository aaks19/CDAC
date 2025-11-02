package com.healthcare.dao;

import java.sql.SQLException;
import java.util.List;

import com.healthcare.pojos.Patient;
import com.healthcare.pojos.PatientAppointment;

public interface PatientDao {
	Patient signIn(String email, String password) throws SQLException;

	List<PatientAppointment> patientAppointment(String email) throws SQLException;

//	PatientAppointment patientAppointment(String email) throws SQLException;
	void cleanUp() throws SQLException;

}
