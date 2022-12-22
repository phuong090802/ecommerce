package com.ute.ecwebapp.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.PhotoService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin("http://localhost:3000")
public class DownloadImage {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private ItemAuctionService itemAuctionService;

	@GetMapping("/source/{itemAuctionId}")
	public ResponseEntity<?> downloadImage(@PathVariable Integer itemAuctionId) {
		var itemAuctionEntity = new ItemAuctionEntity();
		var itemAuctionDto = itemAuctionService.getItemAuctionById(itemAuctionId);
		BeanUtils.copyProperties(itemAuctionDto, itemAuctionEntity);
		var photoEntity = photoService.getByitemAuctionEntity(itemAuctionEntity);
		var mime = photoEntity.getMime();
		var contentType = mime.equals(MediaType.IMAGE_PNG_VALUE) ? MediaType.IMAGE_PNG
				: mime.equals(MediaType.IMAGE_JPEG_VALUE) ? MediaType.IMAGE_JPEG : MediaType.IMAGE_GIF;
		return ResponseEntity.status(HttpStatus.OK).contentType(contentType).body(photoEntity.getPhotoData());
	}
}
