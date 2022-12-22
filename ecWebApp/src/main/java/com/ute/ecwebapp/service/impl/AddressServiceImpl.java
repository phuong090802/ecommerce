package com.ute.ecwebapp.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.AddressRepository;
import com.ute.ecwebapp.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void createAddress(Set<AddressEntity> SetAddressEntity) {
		addressRepository.saveAll(SetAddressEntity);
	}

	@Override
	public Set<AddressEntity> getAllByuser(UserEntity user) {
		return new HashSet<>(addressRepository.findAllByuser(user));
	}

	@Override
	public void updateAddress(UserDto userDto, UserEntity userEntity) {
		addressRepository.saveAll(userDto.getAddress().stream().map(address -> new AddressEntity(address.getAddressId(),
				address.getFullAddress(), address.getState(), userEntity)).collect(Collectors.toSet()));
	}

	@Override
	public void createAddress(AddressEntity addressEntity) {
		addressRepository.save(addressEntity);
	}

	@Override
	public AddressEntity getLastByUser(UserEntity userEntity) {
		return addressRepository.findTopByUserOrderByUserDesc(userEntity)
				.orElseThrow(() -> new BadRequestException("Could not found the addresss with user."));
	}

	@Override
	public void updateAddress(AddressEntity addressEntity) {
		addressRepository.save(addressEntity);
	}

	@Override
	public void updateAddress(Set<AddressEntity> SetAddressEntity) {
		addressRepository.saveAll(SetAddressEntity);
	}
}
