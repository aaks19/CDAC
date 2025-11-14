package com.exam.custom_exception;

public class ResourseAlreadyExistException extends RuntimeException {
	public ResourseAlreadyExistException(String msg) {
		super(msg);
	}
}
