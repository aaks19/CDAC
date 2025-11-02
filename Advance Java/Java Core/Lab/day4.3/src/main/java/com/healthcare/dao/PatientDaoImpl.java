package com.healthcare.dao;

import static com.healthcare.utils.DBUtils.closeConnection;
import static com.healthcare.utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.healthcare.pojos.Patient;
import com.healthcare.pojos.PatientAppointment;

public class PatientDaoImpl implements PatientDao {
	// state
	private Connection cn;
	private PreparedStatement pst1;
	
	//for patient appointment
	private PreparedStatement pst2;

	public PatientDaoImpl() throws SQLException {
		// open cn
		cn = openConnection();
		// create pst1 - login
		pst1 = cn.prepareStatement("select * from patients where email=? and password=?");
		
		
		//Patient appointment display;
		
		pst2 = cn.prepareStatement("select p.id, p.name,d.name, a.appointment_datetime from patients p inner join appointments a on p.id = a.patient_id inner join doctors d on a.doctor_id = d.id where p.email = ?");
		
		System.out.println("patient dao created");

	}

	@Override
	public Patient signIn(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			// int id, String name, String email, String phone, Date dob
			if (rst.next()) {
				return new Patient(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(5), rst.getDate(6));
			}
		}
		return null;
	}

	@Override
	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
			pst1 = null;
		}
		closeConnection();
		System.out.println("patient dao cleaned up");

	}

	@Override
	public List<PatientAppointment> patientAppointment(String email) throws SQLException {
		List<PatientAppointment> patientAppointment = new ArrayList<>();
		pst2.setString(1, email);
		try(ResultSet rst = pst2.executeQuery()){
			while(rst.next()) {
//				return new PatientAppointment(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getTimestamp(4));
				patientAppointment.add( new PatientAppointment(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getTimestamp(4)));
			}
		}
		return patientAppointment;
	}

}
