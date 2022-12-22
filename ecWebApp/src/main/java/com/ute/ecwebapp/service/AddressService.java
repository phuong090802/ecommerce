package com.ute.ecwebapp.service;

import java.util.Set;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;

public interface AddressService {
	void createAddress(Set<AddressEntity> SetAddressEntity);

	Set<AddressEntity> getAllByuser(UserEntity user);

	void updateAddress(UserDto userDto, UserEntity userEntity);

	void createAddress(AddressEntity addressEntity);
	
	AddressEntity getLastByUser(UserEntity userEntity);
	
	void updateAddress(AddressEntity addressEntity);
	
	void updateAddress(Set<AddressEntity> SetAddressEntity);

}
