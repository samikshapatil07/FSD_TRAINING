package com.springboot.lms.exception;

public class NotEnrolledInCourseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;

	public NotEnrolledInCourseException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	
	
}