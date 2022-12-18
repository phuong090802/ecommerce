package com.ute.ecwebapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ute.ecwebapp.dto.ResponseDTO;
import com.ute.ecwebapp.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
		log.info("Bad Request Found {} .", exception.getMessage(), exception);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage(exception.getMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception e) {
		log.info("Unknown error occur {} .", e.getMessage(), e);
		return new ResponseEntity<>(ResponseDTO.builder().responseMessage(e.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
