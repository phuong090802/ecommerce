package com.ute.ecwebapp.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.UserDto;

public interface UserService {
	void createAccount(String json) throws JsonMappingException, JsonProcessingException;

	List<UserDto> getAllUsers();

	UserDto getUserById(Integer userId);

	void updateUser(String json, Integer userId) throws JsonMappingException, JsonProcessingException;

	void deleteUser(Integer userId);

}
