package com.ute.ecwebapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private Integer addressId;
	
	private String address;

	private String state;
	
	private UserDto user;
}
