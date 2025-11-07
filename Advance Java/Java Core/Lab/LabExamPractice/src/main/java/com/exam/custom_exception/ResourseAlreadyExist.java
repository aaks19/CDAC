package com.exam.custom_exception;

public class ResourseAlreadyExist extends RuntimeException {

	public ResourseAlreadyExist (String msg){
		super(msg);
	}
}
