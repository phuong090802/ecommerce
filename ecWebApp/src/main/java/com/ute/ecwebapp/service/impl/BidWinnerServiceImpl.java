package com.ute.ecwebapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import static com.ute.ecwebapp.config.Constraint.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.BidEntity;
import com.ute.ecwebapp.entity.BidWinnerEntity;
import com.ute.ecwebapp.entity.BidWinnerEntityId;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.repository.BidWinnerRepository;
import com.ute.ecwebapp.service.BidService;
import com.ute.ecwebapp.service.BidWinnerService;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.BidWinnerUtil;
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

	@Autowired
	private BidWinnerUtil bidWinnerUtil;

	@Autowired
	private BidService bidService;

	@Override
	public void createBidWinner(String json) throws JsonMappingException, JsonProcessingException {
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
	}

	@Override
	public void updateBidWinner(String json, Integer id) throws JsonMappingException, JsonProcessingException {
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
	}

	@Scheduled(fixedRate = 60000, initialDelay = 1000)
	@Override
	public void autoUpdateBidWinner() {
		var bidWinnerEntity = new BidWinnerEntity();
		var bidWinnerEntityId = new BidWinnerEntityId();
		List<ItemAuctionEntity> listItemAuction = itemAuctionService.getAllItemAuctionsByStatus();
		for (var itemAuction : listItemAuction) {
			if (itemAuction.getEndDate().getTime() < System.currentTimeMillis()) {
				var sellerEntity = bidWinnerUtil.getSeller(itemAuction);
				var buyerEntity = bidWinnerUtil.getBuyer(itemAuction);
				List<AddressEntity> addressBuyer = new ArrayList<>(buyerEntity.getAddress());
				List<AddressEntity> addressSeller = new ArrayList<>(sellerEntity.getAddress());
				if (addressBuyer.get(0).getState().matches(addressSeller.get(0).getState())) {
					bidWinnerEntity.setShipCost(0D);
				} else if (addressBuyer.get(0).getDegree() == addressSeller.get(0).getDegree()) {
					bidWinnerEntity.setShipCost(SAME_URBAN);
				} else if (addressBuyer.get(0).getDegree() > addressSeller.get(0).getDegree()
						|| addressBuyer.get(0).getDegree() < addressSeller.get(0).getDegree()) {
					bidWinnerEntity.setShipCost(NOT_THE_SAME_URBAN);
				}
				bidWinnerEntityId.setBuyer(buyerEntity);
				bidWinnerEntityId.setItemAuction(itemAuction);
				bidWinnerEntity.setValue(bidWinnerUtil.getMaxPrice(new ArrayList<>(itemAuction.getBids())));
				bidWinnerEntity.setBidWinnerId(bidWinnerEntityId);
				bidWinnerRepository.save(bidWinnerEntity);
				itemAuction.setStatus(false);
				List<BidEntity> listBidEntity = new ArrayList<>(buyerEntity.getBids());
				for (var bid : listBidEntity) {
					if (bid.getItemAuction().getItemAuctionId() == bidWinnerEntityId.getItemAuction()
							.getItemAuctionId()) {
						bid.setStatus(1);
						bidService.updateBid(bid);
					}
				}
				List<BidEntity> _listBidEntity = new ArrayList<>(itemAuction.getBids());
				for (var bid : _listBidEntity) {
					if (bid.getUser().getUserId() != buyerEntity.getUserId()) {
						bid.setStatus(0);
						bidService.updateBid(bid);
					}
				}
				itemAuctionService.updateItemAuction(itemAuction);
			}
			for (var bid : itemAuction.getBids()) {
				if (bid.getValue() >= itemAuction.getCurrentPrice()) {
					var sellerEntity = bidWinnerUtil.getSeller(itemAuction);
					var buyerEntity = bidWinnerUtil.getBuyer(itemAuction);
					List<AddressEntity> addressBuyer = new ArrayList<>(buyerEntity.getAddress());
					List<AddressEntity> addressSeller = new ArrayList<>(sellerEntity.getAddress());
					if (addressBuyer.get(0).getState().matches(addressSeller.get(0).getState())) {
						bidWinnerEntity.setShipCost(0D);
					} else if (addressBuyer.get(0).getDegree() == addressSeller.get(0).getDegree()) {
						bidWinnerEntity.setShipCost(SAME_URBAN);
					} else if (addressBuyer.get(0).getDegree() > addressSeller.get(0).getDegree()
							|| addressBuyer.get(0).getDegree() < addressSeller.get(0).getDegree()) {
						bidWinnerEntity.setShipCost(NOT_THE_SAME_URBAN);
					}

					bidWinnerEntityId.setBuyer(buyerEntity);
					bidWinnerEntityId.setItemAuction(itemAuction);
					bidWinnerEntity.setValue(bid.getValue());
					bidWinnerEntity.setBidWinnerId(bidWinnerEntityId);
					bidWinnerRepository.save(bidWinnerEntity);
					itemAuction.setStatus(false);
					itemAuctionService.updateItemAuction(itemAuction);
					List<BidEntity> listBidEntity = new ArrayList<>(buyerEntity.getBids());
					for (var bidEntity : listBidEntity) {
						if (bidEntity.getItemAuction().getItemAuctionId() == bidWinnerEntityId.getItemAuction()
								.getItemAuctionId()) {
							bidEntity.setStatus(1);
							bidService.updateBid(bid);
						}
					}
					List<BidEntity> _listBidEntity = new ArrayList<>(itemAuction.getBids());
					for (var bidEntity : _listBidEntity) {
						if (bidEntity.getUser().getUserId() != buyerEntity.getUserId()) {
							bidEntity.setStatus(0);
							bidService.updateBid(bid);
						}
					}
				}
			}
		}
	}
}
