package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.entity.BidWinnerEntity;
import com.ute.ecwebapp.entity.BidWinnerEntityId;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.repository.BidWinnerRepository;
import com.ute.ecwebapp.service.BidWinnerService;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;

@Service
public class BidWinnerServiceImpl implements BidWinnerService {

	@Autowired
	private BidWinnerRepository bidWinnerRepository;

	@Autowired
	private ItemAuctionService itemAuctionService;

	@Autowired
	private UserService userService;

	@Autowired
	private DtoMapper dtoMapper;

	@Override
	public String createBidWinner(String json) throws JsonMappingException, JsonProcessingException {
		var bidWinnerEntity = new BidWinnerEntity();
		var bidWinnerDto = dtoMapper.convertToBidWinnerDto(json);
		BeanUtils.copyProperties(bidWinnerDto, bidWinnerEntity);
		Integer itemAuctionId = dtoMapper.convertToInteger(json, "itemAuctionId");
		Integer buyId = dtoMapper.convertToInteger(json, "buyId");
		var itemAuctionEntity = new ItemAuctionEntity();
		var buyer = new UserEntity();
		BeanUtils.copyProperties(itemAuctionService.getItemAuctionById(itemAuctionId), itemAuctionEntity);
		BeanUtils.copyProperties(userService.getUserById(buyId), buyer);
		var bidWinnerEntityId = new BidWinnerEntityId();
		bidWinnerEntityId.setItemAuction(itemAuctionEntity);
		bidWinnerEntityId.setBuyer(buyer);
		bidWinnerEntity.setBidWinnerId(bidWinnerEntityId);
		bidWinnerRepository.save(bidWinnerEntity);
		return json;
	}

	@Override
	public String updateBidWinner(String json, Integer id) throws JsonMappingException, JsonProcessingException {
		var bidWinnerEntity = new BidWinnerEntity();
		var bidWinnerDto = dtoMapper.convertToBidWinnerDto(json);
		bidWinnerEntity.setShipCost(bidWinnerDto.getShipCost());
		bidWinnerEntity.setValue(bidWinnerDto.getValue());
		Integer itemAuctionId = dtoMapper.convertToInteger(json, "itemAuctionId");
		Integer buyId = dtoMapper.convertToInteger(json, "buyId");
		var itemAuctionEntity = new ItemAuctionEntity();
		var buyer = new UserEntity();
		BeanUtils.copyProperties(itemAuctionService.getItemAuctionById(itemAuctionId), itemAuctionEntity);
		BeanUtils.copyProperties(userService.getUserById(buyId), buyer);
		var bidWinnerEntityId = new BidWinnerEntityId();
		bidWinnerEntityId.setItemAuction(itemAuctionEntity);
		bidWinnerEntityId.setBuyer(buyer);
		bidWinnerEntity.setBidWinnerId(bidWinnerEntityId);
		bidWinnerRepository.save(bidWinnerEntity);
		return json;
	}
}
