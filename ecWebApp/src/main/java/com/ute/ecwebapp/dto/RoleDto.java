package com.ute.ecwebapp.dto;

import java.util.List;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	@Null
	private Integer roleId;
	
	@NotNull
	private String roleName;
	
	@Null
	private List<AccountDto> account;

}
