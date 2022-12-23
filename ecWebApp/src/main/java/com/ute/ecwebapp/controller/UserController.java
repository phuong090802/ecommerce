package com.ute.ecwebapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.*;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody String json)
			throws BeansException, IOException, InterruptedException {
		userService.createAccount(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Register account successfully.").build(),
				HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId) {
		return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/user/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody String json, @PathVariable Integer userId)
			throws IOException, InterruptedException {
		userService.updateUser(json, userId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update infomation successfully.").build(),
				HttpStatus.OK);
	}

	@PutMapping("/user/forget")
	public ResponseEntity<?> updateUser(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		userService.updateUser(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update password successfully.").build(),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@PutMapping("/user/password")
	public ResponseEntity<?> updatePassword(@RequestBody String json, @PathVariable Integer userId)
			throws JsonMappingException, JsonProcessingException {
		accountService.updatePassword(userId, json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update password successfully.").build(),
				HttpStatus.OK);
	}
}
