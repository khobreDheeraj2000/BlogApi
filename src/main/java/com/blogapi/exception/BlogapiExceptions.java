package com.blogapi.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapi.dto.ApiResponse;

@RestControllerAdvice
public class BlogapiExceptions {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse> noSuchElementExceptionHandler(NoSuchElementException ex) {
		ApiResponse response = new ApiResponse();
		response.setStatus(500);
		response.setMassage(ex.getMessage());
		return ResponseEntity.ok(response);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		ApiResponse response = new ApiResponse();
		response.setStatus(400);
		response.setMassage(ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
		
	}
}
