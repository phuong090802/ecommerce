package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.BidDto;
import com.ute.ecwebapp.entity.BidEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;

public interface BidService {

	void createBid(String json) throws JsonMappingException, JsonProcessingException;

	void updateBid(String json, Integer id) throws JsonMappingException, JsonProcessingException;

	BidDto getById(Integer id);

	void createBid(BidEntity bidEntity);
	

	void updateBid(BidDto bidDto, Integer itemAuctionId);
	
	BidEntity getLastByItemAuctionEntity(ItemAuctionEntity itemAuctionEntity);

}
