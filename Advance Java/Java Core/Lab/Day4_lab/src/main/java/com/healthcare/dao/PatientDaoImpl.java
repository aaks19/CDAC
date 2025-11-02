package com.healthcare.dao;

import static com.healthcare.utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.healthcare.pojos.Patient;

public class PatientDaoImpl implements PatientDao {

	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;

	public PatientDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from Patients where email = ? and password=?");
		/*
		 * Display patient details born between start date & end date i/p - start date ,
		 * end date o/p - List of patient details
		 * 
		 */
		pst2 = cn.prepareStatement("select * from Patients where dob between ? and ?");

		/*
		 * Delete Patient Details i/p - patient id o/p - a message (success | failure)
		 */
		pst3 = cn.prepareStatement("delete from Patients where id = ?");
		System.out.println("Patient DOA Created");
	}

	@Override
	public Patient signIn(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next()) {
				// id | name | email | password | phone | dob

				return new Patient(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDate(6));
			}
		}
		return null;
	}

	@Override
	public List<Patient> displayPatientBetweenDate(String startDate, String enddate) throws SQLException {
		List<Patient> patients = new ArrayList<>();
		Date sd = Date.valueOf(startDate);
		Date ed = Date.valueOf(enddate);
		pst2.setDate(1, sd);
		pst2.setDate(2, ed);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
				patients.add(new Patient(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDate(6)));
			}
//			return patients;
		}
		return patients;
	}

	@Override
	public String deletePatient(int id) throws SQLException {
		pst3.setInt(1, id);
		int rowCount = pst3.executeUpdate();
		if (rowCount == 1) {
			return "Successfully deleted";
		}
		return "Id not Found";
	}

	@Override
	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
			pst1 = null;
		}
		if (pst2 != null) {
			pst2.close();
			pst2 = null;
		}
		if (pst3 != null) {
			pst3.close();
			pst3 = null;
		}
		
	}

}
