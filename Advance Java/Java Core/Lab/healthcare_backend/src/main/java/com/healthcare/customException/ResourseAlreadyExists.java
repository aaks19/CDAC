package com.healthcare.customException;

public class ResourseAlreadyExists extends RuntimeException {
	public ResourseAlreadyExists(String msg) {
		super(msg);
	}
}
