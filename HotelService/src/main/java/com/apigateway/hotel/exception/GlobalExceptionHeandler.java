package com.apigateway.hotel.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHeandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, Object> map=Map.of("message",ex.getMessage(),"success",false,"status",HttpStatus.NOT_FOUND);
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}

}
