package com.ute.ecwebapp.service;

import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;

public interface AccountService {
	void createAccount(AccountEntity accountEntity);

	boolean accountNameExist(String accountName);

	AccountEntity getByUser(UserEntity userEntity);

	void updateAccount(AccountEntity accountEntity);
}
