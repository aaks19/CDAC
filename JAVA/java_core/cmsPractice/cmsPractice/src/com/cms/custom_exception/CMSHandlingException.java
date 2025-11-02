package com.cms.custom_exception;

//extend exception class

@SuppressWarnings("serial")
public class CMSHandlingException extends Exception {

	//constructor
	public CMSHandlingException(String mssg)
	{
		//mssg goes to exception class
		super(mssg);
	}
	
}
