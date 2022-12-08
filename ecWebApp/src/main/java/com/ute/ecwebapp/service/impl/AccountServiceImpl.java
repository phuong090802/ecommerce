package com.ute.ecwebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.repository.AccountRepository;
import com.ute.ecwebapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void createAccount(AccountEntity accountEntity) {
		accountRepository.save(accountEntity);
	}

	@Override
	public boolean accountNameExist(String accountName) {
		return accountRepository.existsByaccountName(accountName);
	}

	@Override
	public AccountEntity getByUser(UserEntity userEntity) {
		return accountRepository.findByuser(userEntity);
	}

	@Override
	public void updateAccount(AccountEntity accountEntity) {
		accountRepository.save(accountEntity);
	}
}
