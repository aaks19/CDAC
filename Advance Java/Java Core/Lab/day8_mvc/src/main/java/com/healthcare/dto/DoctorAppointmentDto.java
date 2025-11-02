package com.healthcare.dto;

import java.sql.Timestamp;

public class DoctorAppointmentDto {
	private int appointmentId;
	private Timestamp appointmentTS;
	private String patientName;
	public DoctorAppointmentDto(int appointmentId, Timestamp appointmentTS, String patientName) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentTS = appointmentTS;
		this.patientName = patientName;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Timestamp getAppointmentTS() {
		return appointmentTS;
	}
	public void setAppointmentTS(Timestamp appointmentTS) {
		this.appointmentTS = appointmentTS;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	@Override
	public String toString() {
		return "DoctorAppointmentDto [appointmentId=" + appointmentId + ", appointmentTS=" + appointmentTS
				+ ", patientName=" + patientName + "]";
	}
	
	
}
