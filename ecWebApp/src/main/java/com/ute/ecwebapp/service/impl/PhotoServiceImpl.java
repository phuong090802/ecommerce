package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.PhotoDto;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.PhotoEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.PhotoRepository;
import com.ute.ecwebapp.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public void savePhoto(PhotoEntity photoEntity) {
		photoRepository.save(photoEntity);
	}

	@Override
	public PhotoEntity getById(Integer photoId) {
		return photoRepository.findById(photoId).orElseThrow(
				() -> new BadRequestException("Could not found the photo with photo id: " + photoId + "."));
	}

	@Override
	public void updatePhoto(PhotoEntity photoEntity) {
		photoRepository.save(photoEntity);
	}

	@Override
	public PhotoDto getByitemAuctionEntityAndPhotoId(ItemAuctionEntity itemAuctionEntity, Integer photoId) {
		var photoDto = new PhotoDto();
		var photoEntity = photoRepository.findByitemAuctionEntityAndPhotoId(itemAuctionEntity, photoId)
				.orElseThrow(() -> new BadRequestException("Could not found the photo with item auction."));
		BeanUtils.copyProperties(photoEntity, photoDto);
		var itemAuctionDto = new ItemAuctionDto();
		BeanUtils.copyProperties(itemAuctionEntity, itemAuctionDto);
		photoDto.setItemAuctionDto(itemAuctionDto);
		return photoDto;
	}
}
