package com.esd.exception;

import org.springframework.stereotype.Component;

@Component
public class UserException extends Exception{
	public UserException() {
		super();
	}
	
	public UserException(String errorMessage) {
		super(errorMessage);
	}
}
