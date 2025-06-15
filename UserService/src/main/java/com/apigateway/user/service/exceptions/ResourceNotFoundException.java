package com.apigateway.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource Not Found Exception");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
