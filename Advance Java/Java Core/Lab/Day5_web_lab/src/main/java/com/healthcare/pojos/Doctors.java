package com.healthcare.pojos;

import java.sql.Date;

public class Doctors {
	private int id;
	private String doc_name;
	private String speciality;
	private String doc_email;
	private String password;
	private Date dob;

	
	//constructor
	public Doctors(String doc_name, String speciality, String doc_email, String password, Date dob) {
		super();
		this.doc_name = doc_name;
		this.speciality = speciality;
		this.doc_email = doc_email;
		this.password = password;
		this.dob = dob;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDoc_name() {
		return doc_name;
	}


	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	public String getDoc_email() {
		return doc_email;
	}


	public void setDoc_email(String doc_email) {
		this.doc_email = doc_email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "Doctors [id=" + id + ", doc_name=" + doc_name + ", speciality=" + speciality + ", doc_email="
				+ doc_email + ", password=" + password + ", dob=" + dob + "]";
	}
	
	
	
}
