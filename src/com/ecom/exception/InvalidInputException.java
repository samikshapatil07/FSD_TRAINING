package com.ecom.exception;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public InvalidInputException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
