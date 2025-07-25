package com.apigateway.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apigateway.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
			String msg=ex.getMessage();
			ApiResponse build=ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();
			return new ResponseEntity<ApiResponse>(build,HttpStatus.NOT_FOUND);
	}

}
