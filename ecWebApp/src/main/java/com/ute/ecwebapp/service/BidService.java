package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BidService {

	void createBid(String json) throws JsonMappingException, JsonProcessingException;

	void updateBid(String json, Integer id) throws JsonMappingException, JsonProcessingException;

}
