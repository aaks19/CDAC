package com.healthcare.pojos;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PatientAppointment {

	private int patient_id;
	private String patient_name;
	private String doctor_name;
	private Timestamp appointment_date;
	public PatientAppointment(int patient_id, String patient_name, String doctor_name, Timestamp appointment_date) {
		super();
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.doctor_name = doctor_name;
		this.appointment_date = appointment_date;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public Timestamp getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(Timestamp appointment_date) {
		this.appointment_date = appointment_date;
	}
	@Override
	public String toString() {
		return "PatientAppointment [patient_id=" + patient_id + ", patient_name=" + patient_name + ", doctor_name="
				+ doctor_name + ", appointment_date=" + appointment_date + "]";
	}
	
	
}
