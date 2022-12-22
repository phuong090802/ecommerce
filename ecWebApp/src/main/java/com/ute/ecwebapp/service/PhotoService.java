package com.ute.ecwebapp.service;

import com.ute.ecwebapp.dto.PhotoDto;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.PhotoEntity;

public interface PhotoService {
	void savePhoto(PhotoEntity photoEntity);

	PhotoEntity getById(Integer photoId);

	void updatePhoto(PhotoEntity photoEntity);

	PhotoDto getByitemAuctionEntity(ItemAuctionEntity itemAuctionEntity);
}
