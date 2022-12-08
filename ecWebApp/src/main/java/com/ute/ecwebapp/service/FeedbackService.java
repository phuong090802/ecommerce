package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface FeedbackService {

	String createFeedback(String json) throws JsonMappingException, JsonProcessingException;

	String updateFeedback(String json, Integer feedbackId) throws JsonMappingException, JsonProcessingException;

}
