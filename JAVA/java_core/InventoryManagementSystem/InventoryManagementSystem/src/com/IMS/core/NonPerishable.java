package com.IMS.core;

import java.util.Objects;

public class NonPerishable extends Inventory{

	private int warrentrPeriod;

	public NonPerishable(String name, Category category, double price, int quantity, int warrentrPeriod) {
		super(name, category, price, quantity);
		this.warrentrPeriod = warrentrPeriod;
	}

	public int getWarrentrPeriod() {
		return warrentrPeriod;
	}

	public void setWarrentrPeriod(int warrentrPeriod) {
		this.warrentrPeriod = warrentrPeriod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(warrentrPeriod);
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
		NonPerishable other = (NonPerishable) obj;
		return warrentrPeriod == other.warrentrPeriod;
	}

	@Override
	public String toString() {
		return super.toString()+" NonPerishable [warrentrPeriod=" + warrentrPeriod + "]";
	}
	
	
}
