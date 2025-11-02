package com.healthcare.dao;

import java.sql.SQLException;
import java.util.List;

import com.healthcare.dto.DoctorAppointmentDto;

public interface DoctorAppointmentDao {
 static List<DoctorAppointmentDto> listAllAppointments(int doctorId) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
 
 String updateAppointmentStatus(int appointmentId, int doctorId) throws SQLException;

void cleanUp() throws SQLException;
}
