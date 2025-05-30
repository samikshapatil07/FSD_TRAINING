package com.jobportal.JobPortal.exception;

public class InvalidUserRoleException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserRoleException(String message) {
        super(message);
    }
}
