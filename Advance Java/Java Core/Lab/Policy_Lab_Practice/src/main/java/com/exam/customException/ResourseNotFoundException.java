package com.exam.customException;

public class ResourseNotFoundException extends RuntimeException {
	public ResourseNotFoundException(String msg) {
		super(msg);
	}
}
