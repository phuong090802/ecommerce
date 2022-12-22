package com.ute.ecwebapp.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import com.ute.ecwebapp.config.RoleName;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	@Null
	private Integer roleId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	
	@Null
	private List<AccountDto> account;

}
