package com.ute.ecwebapp.util;

import static com.ute.ecwebapp.config.Constraint.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.PhotoDto;
import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.PhotoEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Component
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
		List<AddressEntity> listAddress = new ArrayList<>();
		for (var address : listAddressDto) {
			var addressEntity = new AddressEntity();
			BeanUtils.copyProperties(address, addressEntity);
			addressEntity.setUser(userEntity);
			for (var state : URBAN) {
				if (address.getState().matches(state)) {
					addressEntity.setDegree(0);
				}
			}
			for (var state : SUBURBANONE) {
				if (address.getState().matches(state)) {
					addressEntity.setDegree(1);
				}
			}
			for (var state : SUBURBANTWO) {
				if (address.getState().matches(state)) {
					addressEntity.setDegree(2);
				}
			}
			listAddress.add(addressEntity);
		}
		return listAddress;
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

	public List<PhotoEntity> convertToListPhotoEntity(List<PhotoDto> listPhotoDto,
			ItemAuctionEntity itemAuctionEntity) {
		List<PhotoEntity> listPhoto = new ArrayList<>();
		for (var photo : listPhotoDto) {
			listPhoto.add(new PhotoEntity(photo.getPhotoId(), photo.getPhotoName(), photo.getPhotoData(),
					photo.getMime(), itemAuctionEntity));
		}
		return listPhoto;
	}

	public List<PhotoDto> convertToListPhotoDto(List<PhotoEntity> listPhotoEntity) {
		List<PhotoDto> listPhoto = new ArrayList<>();
		for (var photo : listPhotoEntity) {
			var itemAuctionDto = new ItemAuctionDto();
			BeanUtils.copyProperties(photo.getItemAuctionEntity(), itemAuctionDto);
			listPhoto.add(new PhotoDto(photo.getPhotoId(), photo.getPhotoName(), photo.getPhotoData(), photo.getMime(),
					itemAuctionDto));
		}
		return listPhoto;
	}
}
