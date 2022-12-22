package com.ute.ecwebapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.BidDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.BidService;
import com.ute.ecwebapp.service.ItemAuctionService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ItemAuctionController {

	@Autowired
	private ItemAuctionService itemAuctionService;

	@Autowired
	private BidService bidService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/item-auction")
	public ResponseEntity<?> createItemAuction(@RequestBody String json,
			@RequestParam("imageSource") MultipartFile multipartFile) throws IllegalStateException, IOException {
		itemAuctionService.createItemAuction(json, multipartFile);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Create item auction successfully.").build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/item-auctions")
	public ResponseEntity<?> getAllItemAuctions() {
		System.out.println(itemAuctionService.getAllItemAuctions());
		return new ResponseEntity<List<ItemAuctionDto>>(itemAuctionService.getAllItemAuctions(), HttpStatus.OK);
	}

	@GetMapping("/item-auction/{itemAuctionId}")
	public ResponseEntity<?> getItemAuctionById(@PathVariable Integer itemAuctionId) {
		return new ResponseEntity<ItemAuctionDto>(itemAuctionService.getItemAuctionById(itemAuctionId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/item-auction/{itemAuctionId}")
	public ResponseEntity<?> updateItemAuction(@RequestBody String json, @PathVariable Integer itemAuctionId,
			@RequestParam("imageSource") MultipartFile multipartFile)
			throws JsonMappingException, JsonProcessingException, IOException {
		itemAuctionService.updateItemAuction(json, itemAuctionId, multipartFile);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Update item auction successfully.").build(),
				HttpStatus.OK);
	}

	@GetMapping("/item-auction/search/{title}")
	public ResponseEntity<?> getAllGenreTitle(@PathVariable String title) {
		return new ResponseEntity<List<ItemAuctionDto>>(itemAuctionService.getAllGenreTitle(title), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/item-auction/bid/{itemAuctionId}")
	public ResponseEntity<?> updateBid(@RequestBody BidDto bidDto, @PathVariable Integer itemAuctionId)
			throws IllegalStateException, IOException {
		bidService.updateBid(bidDto, itemAuctionId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Add new bid successfully.").build(),
				HttpStatus.CREATED);
	}
}
