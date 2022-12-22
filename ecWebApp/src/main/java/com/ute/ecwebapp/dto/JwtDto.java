package com.ute.ecwebapp.dto;

import java.util.Date;

import lombok.*;

@Builder
@Data
public class JwtDto {

	private String subject;

	private Date expirationDate;

	private String role;
}