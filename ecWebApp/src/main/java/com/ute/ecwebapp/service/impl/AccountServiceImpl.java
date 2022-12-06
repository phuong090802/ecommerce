package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.AccountDto;
import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.repository.AccountRepository;
import com.ute.ecwebapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		AccountEntity accountEntity = new AccountEntity();
		BeanUtils.copyProperties(accountDto, accountEntity);
		accountRepository.save(accountEntity);
		return accountDto;
	}
}
