package com.exam.customException;

public class ResourseAlreadyExistException extends RuntimeException{
	public ResourseAlreadyExistException(String msg) {
		super(msg);
	}
}
