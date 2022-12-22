package com.ute.ecwebapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.entity.ItemAuctionEntity;

public interface ItemAuctionService {
	void createItemAuction(String json, MultipartFile multipartFile)
			throws IllegalStateException, JsonMappingException, JsonProcessingException, IOException;

	List<ItemAuctionDto> getAllItemAuctions();

	ItemAuctionDto getItemAuctionById(Integer itemAuctionId);

	void updateItemAuction(String json, Integer itemAuctionId, MultipartFile multipartFile)
			throws JsonMappingException, JsonProcessingException, IOException;

	void deleteItemAuction(Integer itemAuctionId);

	List<ItemAuctionDto> getAllGenreTitle(String title);

	void updateItemAuction(MultipartFile multipartFile, Integer itemAuctionId) throws IOException;

	void createItemAuction(ItemAuctionEntity itemAuctionEntity);

	void updateItemAuctionId(ItemAuctionDto itemAuctionDto, Integer genreId);

	List<ItemAuctionEntity> getAllItemAuctionsByStatus();
}
