package com.whr.dms.exceptions;

public class ParameterCheckException extends Exception {
	
	private static final long serialVersionUID = 6618144733345206307L;
	
	public ParameterCheckException(){
		super();
	}
	
	public ParameterCheckException(String message) {
		super(message);
	}

	public ParameterCheckException(Throwable cause) {
		super(cause);
	}

	public ParameterCheckException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
