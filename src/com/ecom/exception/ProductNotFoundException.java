package com.ecom.exception;

public class ProductNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String message;

	public ProductNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
