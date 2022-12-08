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
import com.ute.ecwebapp.service.BidWinnerService;

@RestController
@RequestMapping("/ec-web-app")
public class BidWinnerController {

	@Autowired
	private BidWinnerService bidWinnerService;

	@PostMapping("/bid-winner")
	public String createBidWinner(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		return bidWinnerService.createBidWinner(json);
	}

	@PutMapping("/bid-winner/{id}")
	public String updateBidWinner(@RequestBody String json, @PathVariable Integer id)
			throws JsonMappingException, JsonProcessingException {
		return bidWinnerService.updateBidWinner(json, id);
	}
}
