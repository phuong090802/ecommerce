package com.ute.ecwebapp.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.GenreService;
import com.ute.ecwebapp.service.ItemAuctionService;

@RestController
@RequestMapping("/api")
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ItemAuctionService itemAuctionService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/genre")
	public ResponseEntity<?> createGenre(@Valid @RequestBody GenreDto genreDto) {
		genreService.createGenre(genreDto);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create genre successfully.").build(),
				HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/genres")
	public ResponseEntity<?> getAllGenres() {
		return new ResponseEntity<List<GenreDto>>(genreService.getAllGenres(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/genre/{genreId}")
	public ResponseEntity<?> getGenreById(@PathVariable Integer genreId) {
		return new ResponseEntity<GenreDto>(genreService.getGenreById(genreId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/genre/{genreId}")
	public ResponseEntity<?> updateGenre(@Valid @RequestBody GenreDto genreDto, @PathVariable Integer genreId) {
		genreService.updateGenre(genreDto, genreId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update genre successfully.").build(),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/genre/search/{genreName}")
	public ResponseEntity<?> getGenreByName(@PathVariable String genreName) {
		return new ResponseEntity<List<GenreDto>>(genreService.getLikeGenreByName(genreName), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/genre/item-auction/{genreId}")
	public ResponseEntity<?> updateBid(@RequestBody ItemAuctionDto itemAuctionDto, @PathVariable Integer genreId)
			throws IllegalStateException, IOException {
		itemAuctionService.updateItemAuctionId(itemAuctionDto, genreId);
		return new ResponseEntity<>(
				ResponseDTO.builder().responseMessage("Add new ItemAuctionId successfully.").build(),
				HttpStatus.CREATED);
	}
}
