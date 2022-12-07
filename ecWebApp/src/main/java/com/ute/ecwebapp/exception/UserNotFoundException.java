package com.ute.ecwebapp.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Integer userId) {
		super("Could not found the user with role id: " + userId + ".");
	}
}
