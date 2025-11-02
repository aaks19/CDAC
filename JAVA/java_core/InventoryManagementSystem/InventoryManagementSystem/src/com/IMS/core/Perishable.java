package com.IMS.core;

import java.time.LocalDate;
import java.util.Objects;

public class Perishable extends Inventory {
	private LocalDate expiryDate;

	public Perishable(String name, Category category, double price, int quantity, LocalDate expiryDate) {
		super(name, category, price, quantity);
		this.expiryDate = expiryDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(expiryDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perishable other = (Perishable) obj;
		return Objects.equals(expiryDate, other.expiryDate);
	}

	@Override
	public String toString() {
		return super.toString() + "Perishable [expiryDate=" + expiryDate + "]";
	}

}
