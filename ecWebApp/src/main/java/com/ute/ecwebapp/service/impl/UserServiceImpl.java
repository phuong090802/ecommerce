package com.ute.ecwebapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.*;
import com.ute.ecwebapp.enums.Role;
import com.ute.ecwebapp.exception.UserNotFoundException;
import com.ute.ecwebapp.repository.*;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.AddressService;
import com.ute.ecwebapp.service.RoleService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;
import com.ute.ecwebapp.util.ConvertListAdress;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DtoMapper dtoMapper;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ConvertListAdress convertListAdress;

	@Autowired
	private RoleService roleService;

	@Override
	public String createAccount(String json) throws JsonMappingException, JsonProcessingException {
		var userDto = dtoMapper.convertToUserDto(json);
		var accountName = userDto.getAccount().getAccountName();
		if (!accountName.isEmpty()) {
			if (accountService.accountNameExist(accountName)) {
				return "Account name: " + accountName + " has already existed.";
			} else {
				var userEntity = new UserEntity();
				BeanUtils.copyProperties(userDto, userEntity);
				AccountEntity accountEntity = new AccountEntity();
				BeanUtils.copyProperties(userDto.getAccount(), accountEntity);
				accountEntity.setRole(roleService.getByName(Role.USER.toString()));
				accountEntity.setUser(userEntity);
				userRepository.save(userEntity);
				if (userDto.getAddress().isEmpty()) {
					addressService
							.createAddress(convertListAdress.convertToAddressEntity(userDto.getAddress(), userEntity));
				}
				accountService.createAccount(accountEntity);
			}
			return json;
		}
		return "Account name is empty.";
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository
				.findAll().stream().map(user -> new UserDto(user.getUserId(), user.getUserName(), user.getEmail(),
						user.getPhone(), convertListAdress.convertToAddressDto(user.getAddress())))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		var userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		var userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		userDto.setAddress(convertListAdress.convertToAddressDto(userEntity.getAddress()));
		return userDto;
	}

	@Override
	public String updateUser(String json, Integer userId) throws JsonMappingException, JsonProcessingException {
		var userDto = dtoMapper.convertToUserDto(json);
		var userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		userEntity.setUserName(userDto.getUserName());
		userEntity.setPhone(userDto.getPhone());
		userEntity.setAddress(convertListAdress.convertToAddressEntity(userDto.getAddress(), userEntity));
		userRepository.save(userEntity);
		addressService.updateAddress(userDto, userEntity);
		return json;
	}

	@Override
	public String deleteUser(Integer userId) {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		var userEntity = new UserEntity();
		BeanUtils.copyProperties(getUserById(userId), userEntity);
		var accountEntity = accountService.getByUser(userEntity);
		accountEntity.setRole(null);
		accountService.updateAccount(accountEntity);
		return "User with user id: " + userId + " has been removed USER role.";
	}
}
