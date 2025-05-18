
package com.lms.exception;

public class InvalidIdException extends Exception{
	private static final long serialVersionUID = 6293585605622409887L;

	private String message;

	public InvalidIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 

}
