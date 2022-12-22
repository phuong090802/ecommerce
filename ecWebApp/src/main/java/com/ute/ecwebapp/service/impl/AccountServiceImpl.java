package com.ute.ecwebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.LoginDto;
import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.AccountRepository;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.util.JwtUtil;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

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
}
