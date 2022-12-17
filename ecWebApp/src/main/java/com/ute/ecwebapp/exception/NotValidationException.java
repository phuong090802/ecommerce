package com.ute.ecwebapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotValidationException {
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> exceptionHandler(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String errorName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMap.put(errorName, errorMessage);
		});
		return errorMap;
	}
}
