package com.ute.ecwebapp.exception;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
}
