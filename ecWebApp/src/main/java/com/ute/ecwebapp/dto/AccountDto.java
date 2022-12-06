package com.ute.ecwebapp.dto;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private Integer accountId;

	private String userName;

	private String password;
	
	private UserDto user;

	public AccountDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
}
