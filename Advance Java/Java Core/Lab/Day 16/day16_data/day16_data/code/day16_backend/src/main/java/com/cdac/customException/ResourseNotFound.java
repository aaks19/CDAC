package com.cdac.customException;

public class ResourseNotFound extends RuntimeException {

	public ResourseNotFound(String msg) {
		super(msg);
	}
}
