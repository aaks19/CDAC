package com.healthcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.healthcare.utils.DBUtils.*;

import com.healthcare.dto.AppointmentDTO;
import com.healthcare.dto.DoctorAppointmentDto;

public class DoctorAppointmentDaoImpl implements DoctorAppointmentDao {
	private Connection cn;
	private PreparedStatement pst1,pst2;
	
	public DoctorAppointmentDaoImpl() throws SQLException{
		cn = openConnection();
		pst1 = cn.prepareStatement("select a.id,a.appointment_datetime,p.name from appointments a inner join patients p on a.patient_id = p.id where a.doctor_id=? and a.status='SCHEDULED' order by a.appointment_datetime desc");
		
		pst2 = cn.prepareStatement("update appointments set status='COMPLETED' where id=? and doctor_id=? and appointment_datetime>now()");
	}
	@Override
	public List<DoctorAppointmentDto> listAllAppointments(int doctorId) throws SQLException {
		List<DoctorAppointmentDto> list = new ArrayList<>();
		pst1.setInt(1, doctorId);
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next()) {
				list.add(new DoctorAppointmentDto(rst.getInt(1),rst.getTimestamp(2),rst.getString(3)));
			}
		}
		return list;
	}

	@Override
	public String updateAppointmentStatus(int appointmentId, int doctorId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
		closeConnection();

	}
}
