package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface FeedbackService {

	void createFeedback(String json) throws JsonMappingException, JsonProcessingException;

	void updateFeedback(String json, Integer feedbackId) throws JsonMappingException, JsonProcessingException;

}
