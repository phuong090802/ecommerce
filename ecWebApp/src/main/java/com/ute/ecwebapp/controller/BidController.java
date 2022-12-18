package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.BidService;

@RestController
@RequestMapping("/api")
public class BidController {

	@Autowired
	private BidService bidService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/bid")
	public ResponseEntity<?> createBid(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		bidService.createBid(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create bid successfully.").build(),
				HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/bid/{id}")
	public ResponseEntity<?> updateBid(@RequestBody String json, @PathVariable Integer id)
			throws JsonMappingException, JsonProcessingException {
		bidService.updateBid(json, id);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update bid successfully.").build(),
				HttpStatus.OK);
	}

}
