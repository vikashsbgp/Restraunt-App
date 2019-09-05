package com.vikash.restraunt.exception.handling;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String exception) {
		super(exception);
	}
	
}
