package com.ute.ecwebapp.service;

import java.util.List;

import com.ute.ecwebapp.dto.GenreDto;

public interface GenreService {
	void createGenre(GenreDto genreDto);

	List<GenreDto> getAllGenres();

	GenreDto getGenreById(Integer genreId);

	void updateGenre(GenreDto genreDto, Integer genreId);
	
	void deleteGenre(Integer genreId);
	
	GenreDto getGenreByName(String genreName);

	List<GenreDto> getLikeGenreByName(String genreName);
	
	
}
