package com.ute.ecwebapp.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Service
public class ConvertListAddress {
	public List<AddressDto> convertToAddressDto(List<AddressEntity> listAddressEntity) {
		return listAddressEntity.stream()
				.map(address -> new AddressDto(address.getAddressId(), address.getFullAddress(), address.getState()))
				.collect(Collectors.toList());
	}

	public List<AddressEntity> convertToAddressEntity(List<AddressDto> listAddressDto, UserEntity userEntity) {
		return listAddressDto.stream()
				.map(address -> new AddressEntity(address.getFullAddress(), address.getState(), userEntity))
				.collect(Collectors.toList());
	}
}
