package com.ute.ecwebapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDto {

	private String emailName;

	private String receiver;

	private String subject;

	private String messegeBody;

}
