package com.apigateway.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource Not Found");
	}
	
	public ResourceNotFoundException(String s) {
		super(s);
	}
	
	

}
