package com.ute.ecwebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.GenreService;

@RestController
@RequestMapping("/api")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@PostMapping("/genre")
	public ResponseEntity<?> createGenre(@Valid @RequestBody GenreDto genreDto) {
		genreService.createGenre(genreDto);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create genre successfully.").build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/genres")
	public ResponseEntity<?> getAllGenres() {
		return new ResponseEntity<List<GenreDto>>(genreService.getAllGenres(), HttpStatus.OK);
	}

	@GetMapping("/genre/{genreId}")
	public ResponseEntity<?> getGenreById(@PathVariable Integer genreId) {
		return new ResponseEntity<GenreDto>(genreService.getGenreById(genreId), HttpStatus.OK);
	}

	@PutMapping("/genre/{genreId}")
	public ResponseEntity<?> updateGenre(@Valid @RequestBody GenreDto genreDto, @PathVariable Integer genreId) {
		genreService.updateGenre(genreDto, genreId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update genre successfully.").build(),
				HttpStatus.OK);
	}

	@DeleteMapping("/genre/{genreId}")
	public ResponseEntity<?> deleteGenre(@PathVariable Integer genreId) {
		genreService.deleteGenre(genreId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Delete genre successfully.").build(),
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("/genre/search/{genreName}")
	public ResponseEntity<?> getGenreByName(@PathVariable String genreName) {
		return new ResponseEntity<List<GenreDto>>(genreService.getLikeGenreByName(genreName), HttpStatus.OK);
	}
}
