package com.ute.ecwebapp.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private Integer roleId;

	private String roleName;

	private List<AccountDto> account;

}
