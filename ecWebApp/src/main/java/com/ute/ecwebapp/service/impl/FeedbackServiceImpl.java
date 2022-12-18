package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.entity.FeedbackEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.FeedbackRepository;
import com.ute.ecwebapp.service.FeedbackService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private DtoMapper dtoMapper;

	@Autowired
	private UserService userService;

	@Override
	public void createFeedback(String json) throws JsonMappingException, JsonProcessingException {
		var feedbackEntity = new FeedbackEntity();
		var feedbackDto = dtoMapper.convertToFeedbackDto(json);
		if (feedbackDto.getRating() <= 5 && feedbackDto.getRating() >= 1) {
			var sellerEntity = new UserEntity();
			var buyerEntity = new UserEntity();
			BeanUtils.copyProperties(feedbackDto, feedbackEntity);
			var sellerId = dtoMapper.convertToInteger(json, "sellerId");
			var buyId = dtoMapper.convertToInteger(json, "buyId");
			BeanUtils.copyProperties(userService.getUserById(sellerId), sellerEntity);
			BeanUtils.copyProperties(userService.getUserById(buyId), buyerEntity);
			feedbackEntity.setSeller(sellerEntity);
			feedbackEntity.setBuyer(buyerEntity);
			feedbackRepository.save(feedbackEntity);
		} else {
			throw new BadRequestException("Rating not between 1 and 5.");
		}
	}

	@Override
	public void updateFeedback(String json, Integer feedbackId) throws JsonMappingException, JsonProcessingException {
		var feedbackEntity = feedbackRepository.findById(feedbackId).orElseThrow(
				() -> new BadRequestException("Could not found the feedback with feedback id: " + feedbackId + "."));
		var feedbackDto = dtoMapper.convertToFeedbackDto(json);
		if (feedbackDto.getRating() <= 5 && feedbackDto.getRating() >= 1) {
			var sellerEntity = new UserEntity();
			var buyerEntity = new UserEntity();
			var sellerId = dtoMapper.convertToInteger(json, "sellerId");
			var buyId = dtoMapper.convertToInteger(json, "buyId");
			BeanUtils.copyProperties(userService.getUserById(sellerId), sellerEntity);
			BeanUtils.copyProperties(userService.getUserById(buyId), buyerEntity);
			feedbackEntity.setRating(feedbackDto.getRating());
			feedbackEntity.setReview(feedbackDto.getReview());
			feedbackEntity.setSeller(sellerEntity);
			feedbackEntity.setBuyer(buyerEntity);
			feedbackRepository.save(feedbackEntity);
		} else {
			throw new BadRequestException("Rating not between 1 and 5.");
		}
	}
}
