package com.ute.ecwebapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeansException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.UserDto;

public interface UserService {
	void createAccount(String json) throws JsonMappingException, JsonProcessingException, BeansException, IOException, InterruptedException;

	List<UserDto> getAllUsers();

	UserDto getUserById(Integer userId);

	void updateUser(String json, Integer userId) throws JsonMappingException, JsonProcessingException;

	void deleteUser(Integer userId);

}
