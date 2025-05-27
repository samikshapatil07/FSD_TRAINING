package com.springboot.lms.exception;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * Add default serial id. getter and constructor
	 */

	private static final long serialVersionUID = 1L;

	private String message;

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	
	
}