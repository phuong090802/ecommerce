package com.ute.ecwebapp.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	@Null
	private Integer addressId;

	@Null
	private String fullAddress;

	@Null
	private String state;

	@NotNull
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
