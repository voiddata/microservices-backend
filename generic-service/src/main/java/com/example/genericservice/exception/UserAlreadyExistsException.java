package com.example.genericservice.exception;

public class UserAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
