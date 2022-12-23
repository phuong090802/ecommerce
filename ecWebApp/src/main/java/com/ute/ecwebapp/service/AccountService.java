package com.ute.ecwebapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.LoginDto;
import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;

public interface AccountService {
	void createAccount(AccountEntity accountEntity);

	boolean accountNameExist(String accountName);

	AccountEntity getByUser(UserEntity userEntity);

	void updateAccount(AccountEntity accountEntity);

	String login(LoginDto loginDto);
	
	void updatePassword(Integer userId, String json) throws JsonMappingException, JsonProcessingException;
}
