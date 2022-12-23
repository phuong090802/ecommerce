package com.ute.ecwebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.LoginDto;
import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.AccountRepository;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.util.DtoMapper;
import com.ute.ecwebapp.util.JwtUtil;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private DtoMapper dtoMapper;

	@Override
	public void createAccount(AccountEntity accountEntity) {
		accountRepository.save(accountEntity);
	}

	@Value("${email.expire}")
	private long expire;

	@Override
	public boolean accountNameExist(String accountName) {
		return accountRepository.existsByuserName(accountName);
	}

	@Override
	public AccountEntity getByUser(UserEntity userEntity) {
		return accountRepository.findByuser(userEntity);
	}

	@Override
	public void updateAccount(AccountEntity accountEntity) {
		accountRepository.save(accountEntity);
	}

	@Override
	public String login(LoginDto loginDto) {
		var accountEntity = accountRepository.findByuserName(loginDto.getUserName())
				.orElseThrow(() -> new BadRequestException(
						"Could not found the account with account name: " + loginDto.getUserName() + "."));
		if (passwordEncoder.matches(loginDto.getPassword(), accountEntity.getPassword())) {
			return jwtUtil.generateAccessToken(accountEntity).toString();
		} else {
			throw new BadRequestException("Invalid account name or password.");
		}
	}

	@Override
	public void updatePassword(Integer userId, String json) throws JsonMappingException, JsonProcessingException {
		String oldPassword = dtoMapper.convertToString(json, "oldPassword");
		String password = dtoMapper.convertToString(json, "password");
		String repeatPassword = dtoMapper.convertToString(json, "repeatPassword");
		var accountEntity = accountRepository.findById(userId).orElseThrow(
				() -> new BadRequestException("Could not found the account with user id: " + userId + "."));
		if (!passwordEncoder.matches(accountEntity.getPassword(), oldPassword)) {
			throw new BadRequestException("Old password is not valid.");
		} else if (!oldPassword.matches(repeatPassword)) {
			throw new BadRequestException("Repeat password is not matches password.");
		} else if (passwordEncoder.matches(accountEntity.getPassword(), oldPassword)
				&& oldPassword.matches(repeatPassword)) {
			accountEntity.setPassword(password);
		}
	}
}
