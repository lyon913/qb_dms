package com.whr.dms.exceptions;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 6618144733345206307L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
