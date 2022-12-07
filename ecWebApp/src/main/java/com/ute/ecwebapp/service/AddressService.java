package com.ute.ecwebapp.service;

import java.util.List;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;

public interface AddressService {
	void createAddress(UserDto userDto, UserEntity userEntity);

	List<AddressEntity> getAllByuser(UserEntity user);
	
	void updateAddress(UserDto userDto, UserEntity userEntity);

}
