package com.ute.ecwebapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RoleNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> exceptionHandler(RoleNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", exception.getMessage());
		return errorMap;
	}
}