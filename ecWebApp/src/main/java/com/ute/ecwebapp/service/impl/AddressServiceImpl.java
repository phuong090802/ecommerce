package com.ute.ecwebapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.repository.AddressRepository;
import com.ute.ecwebapp.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void createAddress(List<AddressEntity> listAddressEntity) {
		addressRepository.saveAll(listAddressEntity);
	}

	@Override
	public List<AddressEntity> getAllByuser(UserEntity user) {
		return addressRepository.findAllByuser(user);
	}

	@Override
	public void updateAddress(UserDto userDto, UserEntity userEntity) {
		addressRepository
				.saveAll(userDto
						.getAddress().stream().map(address -> new AddressEntity(address.getAddressId(),
								address.getFullAddress(), address.getState(), userEntity))
						.collect(Collectors.toList()));
	}
}
