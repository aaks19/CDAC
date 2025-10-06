package com.LMS.core;

import java.time.LocalDate;
import java.util.Objects;

public class Members {
	private int memberId;
	private String name;
	private LocalDate dateOfMembership;
	private String phoneNumber;
	private String aadhaarCard;
	
	private static int idCounter;

	public Members(String name, LocalDate dateOfMembership, String phoneNumber, String aadhaarCard) {
		super();
		this.memberId = ++idCounter;
		this.name = name;
		this.dateOfMembership = dateOfMembership;
		this.phoneNumber = phoneNumber;
		this.aadhaarCard = aadhaarCard;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfMembership() {
		return dateOfMembership;
	}

	public void setDateOfMembership(LocalDate dateOfMembership) {
		this.dateOfMembership = dateOfMembership;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAadhaarCard() {
		return aadhaarCard;
	}

	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = aadhaarCard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadhaarCard, dateOfMembership, memberId, name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Members other = (Members) obj;
		return Objects.equals(aadhaarCard, other.aadhaarCard)
				&& Objects.equals(dateOfMembership, other.dateOfMembership) && memberId == other.memberId
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Members [memberId=" + memberId + ", name=" + name + ", dateOfMembership=" + dateOfMembership
				+ ", phoneNumber=" + phoneNumber + ", aadhaarCard=" + aadhaarCard + "]";
	}
	
	
	
	
}
