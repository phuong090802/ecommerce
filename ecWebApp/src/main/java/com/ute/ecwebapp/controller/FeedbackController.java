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
import com.ute.ecwebapp.service.FeedbackService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/feedback")
	public ResponseEntity<?> createFeedback(@RequestBody String json)
			throws JsonMappingException, JsonProcessingException {
		feedbackService.createFeedback(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create feedback successfully.").build(),
				HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/feedback/{feedbackId}")
	public ResponseEntity<?> updateFeedback(@RequestBody String json, @PathVariable Integer feedbackId)
			throws JsonMappingException, JsonProcessingException {
		feedbackService.updateFeedback(json, feedbackId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update feedback successfully.").build(),
				HttpStatus.OK);
	}
}
