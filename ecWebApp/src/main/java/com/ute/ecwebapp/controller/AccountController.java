package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.LoginDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.UserService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		var token = accountService.login(loginDto);
		return new ResponseEntity<>(ResponseDTO.builder().json(token).responseMessage("Login successful.").build(),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER', 'ADMIN')")
	@PutMapping("/forget")
	public ResponseEntity<?> forget(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		userService.forget(json);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Please check your email.").build(),
				HttpStatus.OK);
	}

}
