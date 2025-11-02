package com.CricketerManagement.core;

import java.util.Objects;

public class Cricketer {
	
//	String name,int age,String email_id,String Phone,int rating
	private int id;
	private String name;
	private int age;
	private String email_id;
	private String phone;
	private double rating;
	
	private static int idcounter = 0;
	
	
	public Cricketer(String name, int age, String email_id, String phone, double rating) {
		super();
		this.id = ++ idcounter;
		this.name = name;
		this.age = age;
		this.email_id = email_id;
		this.phone = phone;
		this.rating = rating;
	}
	
	public Cricketer(String email_id) {
		this.email_id = email_id;
	}


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




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public String getEmail_id() {
		return email_id;
	}




	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public double getRating() {
		return rating;
	}




	public void setRating(double rating) {
		this.rating = rating;
	}




	@Override
	public String toString() {
		return "Cricketer ["+"ID = "+id+" name = " + name + ", age = " + age + ", email_id = " + email_id + ", phone = " + phone + ", rating = "
				+ rating + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cricketer) {
			Cricketer c = (Cricketer)obj;
			return this.email_id.equals(c.email_id);
		}
		return false;
	}
	
	public int compareTo(Cricketer anotherEmail) {
		return this.email_id.compareTo(anotherEmail.email_id);
	}
	
	
	
	
	
	
}
