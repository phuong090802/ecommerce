package com.ute.ecwebapp.exception;

public class ItemActionNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ItemActionNotFoundException(Integer itemAuctionId) {
		super("Could not found the item auction with item auction id: " + itemAuctionId + ".");
	}
}
