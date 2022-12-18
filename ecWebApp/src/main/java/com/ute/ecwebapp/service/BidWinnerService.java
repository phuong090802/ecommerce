package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BidWinnerService {

	void createBidWinner(String json) throws JsonMappingException, JsonProcessingException;

	void updateBidWinner(String json, Integer id) throws JsonMappingException, JsonProcessingException;

}
