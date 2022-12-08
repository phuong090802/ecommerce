package com.ute.ecwebapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.service.ItemAuctionService;

@RestController
@RequestMapping("/ec-web-app")
public class ItemAuctionController {

	@Autowired
	private ItemAuctionService itemAuctionService;

	@PostMapping("/item-auction")
	public String createItemAuction(@RequestBody String json, @RequestParam("imageSource") MultipartFile multipartFile)
			throws IllegalStateException, IOException {
		return itemAuctionService.createItemAuction(json, multipartFile);
	}

	@GetMapping("/item-auctions")
	public List<ItemAuctionDto> getAllItemAuctions() {
		return itemAuctionService.getAllItemAuctions();
	}

	@GetMapping("/item-auction/{itemAuctionId}")
	public ItemAuctionDto getItemAuctionById(@PathVariable Integer itemAuctionId) {
		return itemAuctionService.getItemAuctionById(itemAuctionId);
	}

	@PutMapping("/item-auction/{itemAuctionId}")
	public String updateItemAuction(@RequestBody String json, @PathVariable Integer itemAuctionId,
			@RequestParam("imageSource") MultipartFile multipartFile)
			throws JsonMappingException, JsonProcessingException, IOException {
		return itemAuctionService.updateItemAuction(json, itemAuctionId, multipartFile);
	}

	@DeleteMapping("/item-auction/{itemAuctionId}")
	public String deleteGenre(@PathVariable Integer itemAuctionId) {
		return itemAuctionService.deleteItemAuction(itemAuctionId);
	}

	@GetMapping("/item-auction/search/{title}")
	public List<ItemAuctionDto> getAllGenreTitle(@PathVariable String title) {
		return itemAuctionService.getAllGenreTitle(title);
	}
}
