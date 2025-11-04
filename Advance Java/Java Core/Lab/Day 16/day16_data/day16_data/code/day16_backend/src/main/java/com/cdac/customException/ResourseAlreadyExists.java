package com.cdac.customException;

@SuppressWarnings("serial")
public class ResourseAlreadyExists extends RuntimeException {
	public ResourseAlreadyExists(String msg) {
		super(msg);
	}
}
