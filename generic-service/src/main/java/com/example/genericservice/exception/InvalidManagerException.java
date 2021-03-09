package com.example.genericservice.exception;

public class InvalidManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidManagerException() {
		super();
	}

	public InvalidManagerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidManagerException(String message) {
		super(message);
	}

	public InvalidManagerException(Throwable cause) {
		super(cause);
	}
	
	

}
