package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.service.FeedbackService;

@RestController
@RequestMapping("/ec-web-app")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/feedback")
	public String createFeedback(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		return feedbackService.createFeedback(json);
	}

	@PutMapping("/feedback/{feedbackId}")
	public String updateFeedback(@RequestBody String json, @PathVariable Integer feedbackId)
			throws JsonMappingException, JsonProcessingException {
		return feedbackService.updateFeedback(json, feedbackId);
	}
}
