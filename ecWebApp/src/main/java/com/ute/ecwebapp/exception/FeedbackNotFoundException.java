package com.ute.ecwebapp.exception;

public class FeedbackNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FeedbackNotFoundException(Integer feedbackId) {
		super("Could not found the feedback with feedback id: " + feedbackId + ".");
	}
}
