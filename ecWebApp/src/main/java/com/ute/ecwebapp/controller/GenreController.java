package com.ute.ecwebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.service.GenreService;

@RestController
@RequestMapping("/ec-web-app")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@PostMapping("/genre")
	public GenreDto createGenre(@Valid @RequestBody GenreDto genreDto) {
		return genreService.createGenre(genreDto);
	}

	@GetMapping("/genres")
	public List<GenreDto> getAllGenres() {
		return genreService.getAllGenres();
	}

	@GetMapping("/genre/{genreId}")
	public GenreDto getGenreById(@PathVariable Integer genreId) {
		return genreService.getGenreById(genreId);
	}

	@PutMapping("/genre/{genreId}")
	public GenreDto updateGenre(@Valid @RequestBody GenreDto genreDto, @PathVariable Integer genreId) {
		return genreService.updateGenre(genreDto, genreId);
	}

	@DeleteMapping("/genre/{genreId}")
	public String deleteGenre(@PathVariable Integer genreId) {
		return genreService.deleteGenre(genreId);
	}

	@GetMapping("/genre/search/{genreName}")
	public List<GenreDto> getGenreByName(@PathVariable String genreName) {
		return genreService.getLikeGenreByName(genreName);
	}
}
