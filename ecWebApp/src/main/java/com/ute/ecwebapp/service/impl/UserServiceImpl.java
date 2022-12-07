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
import com.ute.ecwebapp.exception.UserNotFoundException;
import com.ute.ecwebapp.repository.*;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.AddressService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;
import com.ute.ecwebapp.util.ListEntityToDto;

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
	private ListEntityToDto mapEntityToDto;

	@Override
	public String createAccount(String json) throws JsonMappingException, JsonProcessingException {
		var userDto = dtoMapper.convertToUserDto(json);
		var accountName = userDto.getAccount().getAccountName();
		if (accountService.accountNameExist(accountName)) {
			return "Account name: " + accountName + " has already existed.";
		} else {
			saveAll(userDto);
		}
		return json;
	}

	public void saveAll(UserDto userDto) {
		var userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userRepository.save(userEntity);
		addressService.createAddress(userDto, userEntity);
		accountService.createAccount(userDto, userEntity);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository
				.findAll().stream().map(user -> new UserDto(user.getUserId(), user.getUserName(), user.getEmail(),
						user.getPhone(), mapEntityToDto.convertAddressDto(user.getAddress())))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		userDto.setAddress(mapEntityToDto.convertAddressDto(userEntity.getAddress()));
		return userDto;
	}

	@Override
	public String updateUser(String json) throws JsonMappingException, JsonProcessingException {
		var userDto = dtoMapper.convertToUserDto(json);
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userRepository.save(userEntity);
		addressService.createAddress(userDto, userEntity);
		return json;
	}

	@Override
	public String deleteUser(Integer userId) {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		userRepository.deleteById(userId);
		return "User with user id: " + userId + " has been deleted success.";
	}
}
