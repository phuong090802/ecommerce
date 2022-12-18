package com.ute.ecwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.*;
import com.ute.ecwebapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		userService.createAccount(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Register account successfully.").build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getGenreById(@PathVariable Integer userId) {
		return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<?> updateGenre(@RequestBody String json, @PathVariable Integer userId)
			throws JsonMappingException, JsonProcessingException {
		userService.updateUser(json, userId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update infomation successfully.").build(),
				HttpStatus.OK);
	}

}
