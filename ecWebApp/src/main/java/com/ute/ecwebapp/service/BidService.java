package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BidService {

	String createBid(String json) throws JsonMappingException, JsonProcessingException;

	String updateBid(String json, Integer id) throws JsonMappingException, JsonProcessingException;

}
