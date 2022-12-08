package com.ute.ecwebapp.util;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.ecwebapp.dto.BidDto;
import com.ute.ecwebapp.dto.BidWinnerDto;
import com.ute.ecwebapp.dto.FeedbackDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.UserDto;

@Service
public class DtoMapper {

	@Autowired
	private ObjectMapper objectMapper;

	public UserDto convertToUserDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, UserDto.class);
	}

	public ItemAuctionDto covertToItemAuctionDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, ItemAuctionDto.class);
	}

	public Integer convertToInteger(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.getInt(key);
	}

	public FeedbackDto convertToFeedbackDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, FeedbackDto.class);
	}

	public BidDto convertToBidDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, BidDto.class);
	}

	public BidWinnerDto convertToBidWinnerDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, BidWinnerDto.class);
	}
}
