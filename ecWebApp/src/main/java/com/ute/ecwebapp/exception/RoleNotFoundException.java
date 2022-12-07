package com.ute.ecwebapp.exception;

public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(Integer roleId) {
		super("Could not found the role with role id: " + roleId + ".");
	}
}
