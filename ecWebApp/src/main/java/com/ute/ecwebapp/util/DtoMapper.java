package com.ute.ecwebapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.ecwebapp.dto.UserDto;

@Service
public class DtoMapper {

	@Autowired
	private ObjectMapper objectMapper;

	public UserDto convertToUserDto(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, UserDto.class);
	}
}
