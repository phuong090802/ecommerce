package com.ute.ecwebapp.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private Integer addressId;

	private String fullAddress;

	private String state;

	private UserDto user;

	public AddressDto(String fullAddress, String state, UserDto user) {
		super();
		this.fullAddress = fullAddress;
		this.state = state;
		this.user = user;
	}

	public AddressDto(Integer addressId, String fullAddress, String state) {
		super();
		this.addressId = addressId;
		this.fullAddress = fullAddress;
		this.state = state;
	}
	
	
}
