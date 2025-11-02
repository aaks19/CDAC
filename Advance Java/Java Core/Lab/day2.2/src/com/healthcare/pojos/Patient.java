package com.healthcare.pojos;

import java.sql.*;

//id | name          | email                    | password | phone      | dob  
public class Patient {
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Date dob;
	
	//generate constructor
	public Patient(){
		
	}
	

	public Patient(int id, String name, String email, String password, String phone, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.dob = dob;
	}


	//getter n setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", dob=" + dob + "]";
	}
	
	
	
}
