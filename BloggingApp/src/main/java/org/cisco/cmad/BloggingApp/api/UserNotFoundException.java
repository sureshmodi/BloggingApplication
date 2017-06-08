package org.cisco.cmad.BloggingApp.api;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3121627706351526424L;
	
	public UserNotFoundException(String message) {
			super(message);
	}

	public UserNotFoundException() {
		super();
		
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		
	}
	
	

}
