package com.healthcare.entities;
//users table -column - id(PK) , first name , last name, email ,password , phone , dob:date , role:enum,image :blob

import java.time.LocalDate;
import jakarta.persistence.*;

public class User {
	
	private Long id;
	private String firstName;
	private String  lastName;
	private String email;
	private String password;
	private String phone;
	private LocalDate dob;
	private UserRole role;
	private byte[] image;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String email, String password, String phone, LocalDate dob,
			UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.dob = dob;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", dob=" + dob + ", role=" + role + "]";
	}
	
	

}
