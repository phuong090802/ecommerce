package com.ute.ecwebapp.dto;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	@Null
	private Integer accountId;

	@NonNull
	private String userName;

	@NotNull
	private String password;

	@NotNull
	private UserDto user;
	
	@Null
	private String otp;
	
	@Null
	private Date expire;

	@Null
	private RoleDto role;

	public AccountDto(String userName, String password, RoleDto role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
}
