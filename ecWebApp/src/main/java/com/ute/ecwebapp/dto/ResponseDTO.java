package com.ute.ecwebapp.dto;

import lombok.*;

@Data
@Builder
public class ResponseDTO<T> {
	private String responseMessage;

	private T json;
}
