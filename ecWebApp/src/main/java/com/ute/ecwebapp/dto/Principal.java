package com.ute.ecwebapp.dto;

import lombok.Data;

@Data
public class Principal {
	private String accountName;
	
	private String token;

	private String role;

}
