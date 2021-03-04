package com.example.demo.exception;

public class LeaveAppliedAlready extends Exception {

	private static final long serialVersionUID = 1L;

	public LeaveAppliedAlready() {
	}

	public LeaveAppliedAlready(String message) {
		super(message);
	}

	public LeaveAppliedAlready(Throwable cause) {
		super(cause);
	}

	public LeaveAppliedAlready(String message, Throwable cause) {
		super(message, cause);
	}

	public LeaveAppliedAlready(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
