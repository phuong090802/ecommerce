package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.service.BidService;

@RestController
@RequestMapping("/ec-web-app")
public class BidController {

	@Autowired
	private BidService bidService;

	@PostMapping("/bid")
	public String createBid(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		return bidService.createBid(json);
	}

	@PutMapping("/bid/{id}")
	public String updateBid(@RequestBody String json, @PathVariable Integer id) throws JsonMappingException, JsonProcessingException {
		return bidService.updateBid(json, id);
	}
}
