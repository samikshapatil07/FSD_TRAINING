package com.ecom.exception;

public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	private String message;

	public CustomerNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
