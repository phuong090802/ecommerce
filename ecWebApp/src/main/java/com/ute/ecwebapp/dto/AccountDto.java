package com.ute.ecwebapp.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	@Null
	private Integer accountId;

	@NonNull
	private String accountName;

	@NotNull
	private String password;

	@NotNull
	private UserDto user;

	@Null
	private RoleDto role;

	public AccountDto(String accountName, String password, RoleDto role) {
		super();
		this.accountName = accountName;
		this.password = password;
		this.role = role;
	}

}
