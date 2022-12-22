package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class AddressController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	@PostMapping("/address/{userId}")
	public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto, @PathVariable Integer userId)
			throws JsonMappingException, JsonProcessingException {
		userService.updateUser(addressDto, userId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Add new address successfully.").build(),
				HttpStatus.CREATED);
	}
}
