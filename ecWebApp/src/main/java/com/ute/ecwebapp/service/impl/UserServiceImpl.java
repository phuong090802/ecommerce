package com.ute.ecwebapp.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.config.RoleName;
import com.ute.ecwebapp.dto.AddressDto;
import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.*;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.*;
import com.ute.ecwebapp.service.AccountService;
import com.ute.ecwebapp.service.AddressService;
import com.ute.ecwebapp.service.EmailService;
import com.ute.ecwebapp.service.RoleService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.DtoMapper;
import com.ute.ecwebapp.util.ValidationUtil;

import net.bytebuddy.utility.RandomString;

import com.ute.ecwebapp.util.ConvertList;

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
	private ConvertList convertListAdress;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ValidationUtil validationUtil;

	@Autowired
	private EmailService emailService;

	@Value("${email.expire}")
	private long expire;

	@Override
	public void createAccount(String json) throws BeansException, IOException, InterruptedException {
		var userDto = dtoMapper.convertToUserDto(json);
		var userName = userDto.getAccount().getUserName();
		if (!validationUtil.validationEmail(userDto.getEmail())) {
			throw new BadRequestException("Email is not valid.");
		}
		if (!validationUtil.validationPhone(userDto.getPhone())) {
			throw new BadRequestException("Phone is not valid.");
		}
		if (userName != null) {
			if (accountService.accountNameExist(userName)) {
				throw new BadRequestException("User name: " + userName + " has already existed.");
			} else {
				var userEntity = new UserEntity();
				BeanUtils.copyProperties(userDto, userEntity);
				AccountEntity accountEntity = new AccountEntity();
				BeanUtils.copyProperties(userDto.getAccount(), accountEntity);
				accountEntity.setPassword(passwordEncoder.encode(userDto.getAccount().getPassword()));
				accountEntity.setRole(roleService.getByName(RoleName.USER));
				accountEntity.setUser(userEntity);
				userRepository.save(userEntity);
				System.out.println(userDto.getAddress().toString());
				if (!userDto.getAddress().isEmpty()) {
					addressService
							.createAddress(convertListAdress.convertToAddressEntity(userDto.getAddress(), userEntity));
				}
				accountService.createAccount(accountEntity);
			}
		} else {
			throw new BadRequestException("Input is not valid.");
		}
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository
				.findAll().stream().map(user -> new UserDto(user.getUserId(), user.getFullName(), user.getEmail(),
						user.getPhone(), convertListAdress.convertToAddressDto(user.getAddress())))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		var userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new BadRequestException("Could not found the user with user id: " + userId + "."));
		var userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		userDto.setAddress(convertListAdress.convertToAddressDto(userEntity.getAddress()));
		return userDto;
	}

	@Override
	public void updateUser(String json, Integer userId) throws IOException, InterruptedException {
		var userDto = dtoMapper.convertToUserDto(json);
		if (validationUtil.validationEmail(userDto.getEmail())) {
			throw new BadRequestException("Email is not valid.");
		}
		if (validationUtil.validationPhone(userDto.getPhone())) {
			throw new BadRequestException("Phone is not valid.");
		} else {
			var userEntity = userRepository.findById(userId).orElseThrow(
					() -> new BadRequestException("Could not found the user with user id: " + userId + "."));
			userEntity.setFullName(userDto.getFullName());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setPhone(userDto.getPhone());
			userEntity.setAddress(convertListAdress.convertToAddressEntity(userDto.getAddress(), userEntity));
			userRepository.save(userEntity);
			addressService.updateAddress(userDto, userEntity);
		}
	}

	@Override
	public void deleteUser(Integer userId) {
		if (!userRepository.existsById(userId)) {
			throw new BadRequestException("Could not found the user with user id: " + userId + ".");
		}
		var userEntity = new UserEntity();
		BeanUtils.copyProperties(getUserById(userId), userEntity);
		var accountEntity = accountService.getByUser(userEntity);
		accountEntity.setRole(null);
		accountService.updateAccount(accountEntity);
	}

	@Override
	public UserDto getByEmail(String email) {
		var userEntity = userRepository.findByemail(email).orElseThrow(
				() -> new BadRequestException("Could not found the user with user with email: " + email + "."));
		var userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		userDto.setAddress(convertListAdress.convertToAddressDto(userEntity.getAddress()));
		return userDto;
	}

	@Override
	public void forget(String json) throws JsonMappingException, JsonProcessingException {
		var email = dtoMapper.convertToString(json, "email");
		var userEntity = userRepository.findByemail(email).orElseThrow(
				() -> new BadRequestException("Could not found the user with user with email: " + email + "."));
		String otp = RandomString.make(8);

		var accountEntity = userEntity.getAccount();
		accountEntity.setExpire(new Date(System.currentTimeMillis() + expire));
		accountEntity.setOtp(otp);
		accountService.updateAccount(accountEntity);
		emailService.sendEmail(email, otp);
	}

	@Override
	public void updateUser(String json) throws JsonMappingException, JsonProcessingException {
		var email = dtoMapper.convertToString(json, "email");
		var code = dtoMapper.convertToString(json, "code");
		var password = dtoMapper.convertToString(json, "password");
		var confirmPassword = dtoMapper.convertToString(json, "confirmPassword");
		var userEntity = userRepository.findByemail(email).orElseThrow(
				() -> new BadRequestException("Could not found the user with user with email: " + email + "."));
		var accountEntity = userEntity.getAccount();
		if (accountEntity == null || accountEntity.getExpire() == null) {
			throw new BadRequestException("OTP is not valid.");
		}
		if (accountEntity.getExpire().getTime() < System.currentTimeMillis()) {
			throw new BadRequestException("OTP expires 100"
					+ (System.currentTimeMillis() - accountEntity.getExpire().getTime()) + " seconds.");
		} else if (!code.matches(accountEntity.getOtp())) {
			throw new BadRequestException("OTP is not valid.");
		} else if (code.matches(accountEntity.getOtp())) {
			if (!password.matches(confirmPassword)) {
				throw new BadRequestException("Password and confirm password does not match.");
			} else {
				accountEntity.setPassword(passwordEncoder.encode(password));
				accountEntity.setOtp(null);
				accountEntity.setExpire(null);
				accountService.updateAccount(accountEntity);
			}
		}
	}

	@Override
	public void updateUser(AddressDto addressDto, Integer userId) {
		var addressEntity = new AddressEntity();
		BeanUtils.copyProperties(addressDto, addressEntity);
		var userEntity = new UserEntity();
		var userDto = userRepository.findById(userId)
				.orElseThrow(() -> new BadRequestException("Could not found the user with user id: " + userId + "."));
		BeanUtils.copyProperties(userDto, userEntity);
		addressEntity.setUser(userEntity);
		addressService.createAddress(addressEntity);
	}
}
