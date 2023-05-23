package com.esd.exception;

public class AdminException extends Exception{
	public AdminException() {
		super();
	}
	
	public AdminException(String errorMessage) {
		super(errorMessage);
	}
}
