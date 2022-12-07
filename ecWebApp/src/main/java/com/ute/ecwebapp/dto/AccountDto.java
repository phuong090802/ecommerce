package com.ute.ecwebapp.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private Integer accountId;

	private String accountName;

	private String password;
	
	private UserDto user;
	
	private RoleDto role;

	public AccountDto(String accountName, String password, RoleDto role) {
		super();
		this.accountName = accountName;
		this.password = password;
		this.role = role;
	}
	
}
