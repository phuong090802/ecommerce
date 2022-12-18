package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.entity.BidEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.BidRepository;
import com.ute.ecwebapp.service.BidService;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private DtoMapper dtoMapper;

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private ItemAuctionService itemAuctionService;

	@Autowired
	private UserService userService;

	@Override
	public void createBid(String json) throws JsonMappingException, JsonProcessingException {
		var bidEntity = new BidEntity();
		var buyerEntity = new UserEntity();
		var itemAuctionEntity = new ItemAuctionEntity();
		var bidDto = dtoMapper.convertToBidDto(json);
		BeanUtils.copyProperties(bidDto, bidEntity);
		var itemAuctionId = dtoMapper.convertToInteger(json, "itemAuctionId");
		var buyId = dtoMapper.convertToInteger(json, "buyId");
		BeanUtils.copyProperties(userService.getUserById(buyId), buyerEntity);
		BeanUtils.copyProperties(itemAuctionService.getItemAuctionById(itemAuctionId), itemAuctionEntity);
		bidEntity.setItemAuction(itemAuctionEntity);
		bidEntity.setUser(buyerEntity);
		bidRepository.save(bidEntity);
	}

	@Override
	public void updateBid(String json, Integer id) throws JsonMappingException, JsonProcessingException {
		var bidEntity = bidRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Could not found the account with Bid:" + id + "."));
		var buyerEntity = new UserEntity();
		var itemAuctionEntity = new ItemAuctionEntity();
		var bidDto = dtoMapper.convertToBidDto(json);
		var itemAuctionId = dtoMapper.convertToInteger(json, "itemAuctionId");
		var buyId = dtoMapper.convertToInteger(json, "buyId");
		BeanUtils.copyProperties(userService.getUserById(buyId), buyerEntity);
		BeanUtils.copyProperties(itemAuctionService.getItemAuctionById(itemAuctionId), itemAuctionEntity);
		bidEntity.setItemAuction(itemAuctionEntity);
		bidEntity.setUser(buyerEntity);
		bidEntity.setTime(bidDto.getTime());
		bidEntity.setValue(bidDto.getValue());
		bidEntity.setStatus(bidDto.getStatus());
		bidRepository.save(bidEntity);
	}
}
