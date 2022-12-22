package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.BidWinnerService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class BidWinnerController {

	@Autowired
	private BidWinnerService bidWinnerService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/bid-winner")
	public ResponseEntity<?> createBidWinner(@RequestBody String json)
			throws JsonMappingException, JsonProcessingException {
		bidWinnerService.createBidWinner(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create bid winner successfully.").build(),
				HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/bid-winner/{id}")
	public ResponseEntity<?> updateBidWinner(@RequestBody String json, @PathVariable Integer id)
			throws JsonMappingException, JsonProcessingException {
		bidWinnerService.updateBidWinner(json, id);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update bid winner successfully.").build(),
				HttpStatus.OK);
	}
}
