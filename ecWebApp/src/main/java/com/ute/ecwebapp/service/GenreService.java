package com.ute.ecwebapp.service;

import java.util.List;

import com.ute.ecwebapp.dto.GenreDto;

public interface GenreService {
	GenreDto createGenre(GenreDto genreDto);

	List<GenreDto> getAllGenres();

	GenreDto getGenreById(Integer genreId);

	GenreDto updateGenre(GenreDto genreDto, Integer genreId);
	
	String deleteGenre(Integer genreId);
	
	GenreDto getGenreByName(String genreName);

	List<GenreDto> getLikeGenreByName(String genreName);
	
	
}
