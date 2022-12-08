package com.ute.ecwebapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.service.ItemAuctionService;

@RestController
@RequestMapping("/image")
public class DownloadImage {

	@Autowired
	private ItemAuctionService itemAuctionService;

	@GetMapping(value = "/source/{photoName}")
	public ResponseEntity<?> downloadImage(@PathVariable String photoName) {
		var itemAuctionEntity = itemAuctionService.findByphoto(photoName);
		var type = itemAuctionEntity.getType();
		var contentType = type.equals(MediaType.IMAGE_PNG_VALUE) ? MediaType.IMAGE_PNG
				: type.equals(MediaType.IMAGE_JPEG_VALUE) ? MediaType.IMAGE_JPEG : MediaType.IMAGE_GIF;
		return ResponseEntity.status(HttpStatus.OK).contentType(contentType).body(itemAuctionEntity.getPhotoData());
	}
}
