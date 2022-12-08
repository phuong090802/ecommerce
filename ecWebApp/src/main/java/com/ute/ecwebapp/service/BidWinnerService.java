package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BidWinnerService {

	String createBidWinner(String json) throws JsonMappingException, JsonProcessingException;

	String updateBidWinner(String json, Integer id) throws JsonMappingException, JsonProcessingException;

}
