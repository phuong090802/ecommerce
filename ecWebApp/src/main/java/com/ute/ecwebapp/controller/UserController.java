package com.ute.ecwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.*;
import com.ute.ecwebapp.service.UserService;

@RestController
@RequestMapping("/ec-web-app")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public String createUser(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		return userService.createAccount(json);
	}

	@GetMapping("/users")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{userId}")
	public UserDto getGenreById(@PathVariable Integer userId) {
		return userService.getUserById(userId);
	}

	@PutMapping("/user/{userId}")
	public String updateGenre(@RequestBody String json, @PathVariable Integer userId)
			throws JsonMappingException, JsonProcessingException {
		return userService.updateUser(json, userId);
	}

	@DeleteMapping("/user/{userId}")
	public String deleteGenre(@PathVariable Integer userId) {
		return userService.deleteUser(userId);
	}
}
