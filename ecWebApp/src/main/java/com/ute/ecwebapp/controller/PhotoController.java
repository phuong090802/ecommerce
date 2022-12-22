package com.ute.ecwebapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.service.ItemAuctionService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class PhotoController {

	@Autowired
	private ItemAuctionService itemAuctionService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/photo/{itemAuctionId}")
	public ResponseEntity<?> createAddress(@RequestParam("imageSource") MultipartFile multipartFile,
			@PathVariable Integer itemAuctionId) throws IOException {
		itemAuctionService.updateItemAuction(multipartFile, itemAuctionId);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage("Add new photo successfully.").build(),
				HttpStatus.CREATED);
	}

}
