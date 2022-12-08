package com.ute.ecwebapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.entity.ItemAuctionEntity;

public interface ItemAuctionService {
	String createItemAuction(String json, MultipartFile multipartFile)
			throws IllegalStateException, JsonMappingException, JsonProcessingException, IOException;

	ItemAuctionEntity findByphoto(String photoName);

	List<ItemAuctionDto> getAllItemAuctions();

	ItemAuctionDto getItemAuctionById(Integer itemAuctionId);

	String updateItemAuction(String json, Integer itemAuctionId, MultipartFile multipartFile)
			throws JsonMappingException, JsonProcessingException, IOException;

	String deleteItemAuction(Integer itemAuctionId);

	List<ItemAuctionDto> getAllGenreTitle(String title);
}
