package com.ute.ecwebapp.service;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.UserEntity;

public interface AccountService {
	void createAccount(UserDto userDto, UserEntity userEntity);
	
	boolean accountNameExist(String accountName);
}
