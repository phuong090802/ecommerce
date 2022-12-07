package com.ute.ecwebapp.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.repository.AccountRepository;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.RoleService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleService roleService;

	private final Integer roleId = 1;

	@Override
	public void createAccount(UserDto userDto, UserEntity userEntity) {
		AccountEntity accountEntity = new AccountEntity();
		BeanUtils.copyProperties(userDto.getAccount(), accountEntity);
		accountEntity.setRole(roleService.getById(roleId));
		accountEntity.setUser(userEntity);
		accountRepository.save(accountEntity);
	}

	@Override
	public boolean accountNameExist(String accountName) {
		return accountRepository.existsByaccountName(accountName);
	}
}
