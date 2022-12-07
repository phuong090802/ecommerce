package com.ute.ecwebapp.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.entity.AddressEntity;

@Service
public class ListEntityToDto {
	public List<AddressDto> convertAddressDto(List<AddressEntity> listAddressEntity) {
		return listAddressEntity.stream()
				.map(address -> new AddressDto(address.getAddressId(), address.getFullAddress(), address.getState()))
				.collect(Collectors.toList());
	}
}
