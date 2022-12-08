package com.ute.ecwebapp.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(Integer accountId) {
		super("Could not found the account with account id: " + accountId + ".");
	}
	
	public AccountNotFoundException(String accountName) {
		super("Could not found the account with account name: " + accountName + ".");
	}
	
	public AccountNotFoundException() {
		super("Could not found the account with account name.");
	}
}
