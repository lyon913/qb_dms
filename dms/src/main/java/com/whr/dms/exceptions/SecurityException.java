package com.whr.dms.exceptions;

public class SecurityException extends Exception {

	private static final long serialVersionUID = 5220825262271954613L;

	public SecurityException(){
		super();
	}
	
	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

}
