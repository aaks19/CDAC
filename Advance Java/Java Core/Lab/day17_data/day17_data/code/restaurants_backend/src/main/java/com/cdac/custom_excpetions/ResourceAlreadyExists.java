package com.cdac.custom_excpetions;

public class ResourceAlreadyExists extends RuntimeException {
	public ResourceAlreadyExists(String errMesg) {
		super(errMesg);
	}
}
