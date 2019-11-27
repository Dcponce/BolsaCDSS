package com.cdspool.main.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = -5382527233414841819L;

	public UserServiceException(String message) {
		super(message);
	}
}
