package com.sms.core;

public enum Course {
	CORE_JAVA(60,120), DBT(50,100), PYTHON(70,200) , MERN(40,60), WEB_JAVA(80,120),DEV_OPS(50,50);
	
	private int minMarks;
	private int minSeat;
	
	private Course(int minMarks,int minSeat) {
		this.minMarks = minMarks;
		this.minSeat = minSeat;
	}

	public int getMinSeat() {
		return minSeat;
	}

	public void setMinSeat(int minSeat) {
		this.minSeat = minSeat;
	}

	public int getMinMarks() {
		return minMarks;
	}
	
	
	@Override
	public String toString() {
		return "Course: "+name()+" minimum marks required = "+ this.minMarks+" Available seat = "+this.minSeat;
	}
}
