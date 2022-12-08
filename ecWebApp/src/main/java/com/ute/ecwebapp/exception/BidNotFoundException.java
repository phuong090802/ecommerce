package com.ute.ecwebapp.exception;

public class BidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BidNotFoundException(Integer id) {
		super("Could not found the bid with account id: " + id + ".");
	}
}
