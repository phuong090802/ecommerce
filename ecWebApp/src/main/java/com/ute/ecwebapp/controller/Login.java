package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ute.ecwebapp.dto.LoginDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.AccountService;

@RestController
@RequestMapping("/api")
public class Login {

	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		var token = accountService.login(loginDto);
		return new ResponseEntity<>(ResponseDTO.builder().json(token).responseMessage("Login successful.").build(),
				HttpStatus.OK);
	}
}
