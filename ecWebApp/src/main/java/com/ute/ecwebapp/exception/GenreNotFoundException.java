package com.ute.ecwebapp.exception;

public class GenreNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GenreNotFoundException(Integer genreId) {
		super("Could not found the genre with genre id: " + genreId + ".");
	}
	
	public GenreNotFoundException(String genreName) {
		super("Could not found the genre with genre name: " + genreName + ".");
	}
}
