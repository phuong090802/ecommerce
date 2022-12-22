package com.ute.ecwebapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.PhotoDto;
import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.PhotoEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Service
public class ConvertList {
	public List<AddressDto> convertToAddressDto(List<AddressEntity> listAddressEntity) {
		List<AddressDto> listAddress = new ArrayList<>();
		for (var address : listAddressEntity) {
			var userDto = new UserDto();
			BeanUtils.copyProperties(address.getState(), userDto);
			listAddress
					.add(new AddressDto(address.getAddressId(), address.getFullAddress(), address.getState(), userDto));
		}
		return listAddress;
	}

	public List<AddressEntity> convertToAddressEntity(List<AddressDto> listAddressDto, UserEntity userEntity) {
		return listAddressDto.stream()
				.map(address -> new AddressEntity(address.getFullAddress(), address.getState(), userEntity))
				.collect(Collectors.toList());
	}

	public List<PhotoDto> convertToPhotoDto(List<PhotoEntity> listPhotoEntity) {
		List<PhotoDto> listPhoto = new ArrayList<>();
		for (var photo : listPhotoEntity) {
			var itemAuctionDto = new ItemAuctionDto();
			BeanUtils.copyProperties(photo.getItemAuctionEntity(), itemAuctionDto);
			listPhoto.add(new PhotoDto(photo.getPhotoId(), photo.getPhotoName(), photo.getPhotoData(), photo.getMime(),
					itemAuctionDto));
		}
		return listPhoto;
	}

	public List<PhotoEntity> convertToPhotoEntity(List<PhotoDto> listPhotoDto, ItemAuctionEntity itemAuctionEntity) {
		List<PhotoEntity> listPhoto = new ArrayList<>();
		for (var photo : listPhotoDto) {
			listPhoto.add(new PhotoEntity(photo.getPhotoId(), photo.getPhotoName(), photo.getPhotoData(),
					photo.getMime(), itemAuctionEntity));
		}
		return listPhoto;
	}
}
