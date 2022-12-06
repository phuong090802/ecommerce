package com.ute.ecwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ute.ecwebapp.dto.AccountDto;
import com.ute.ecwebapp.service.AccountService;

@RestController
@RequestMapping("/ec-web-app")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/account")
	public AccountDto createAccount(@RequestBody AccountDto accountDto) {
		System.out.println(accountDto.toString());
		return accountService.createAccount(accountDto);
	}
}
