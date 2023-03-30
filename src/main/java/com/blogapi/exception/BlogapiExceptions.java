package com.blogapi.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapi.dto.ApiResponse;
import com.blogapi.dto.ApiResponseTest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

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
	

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> invalidCredentials(){
		String message = "Invalid username or password, please check your credentials";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> expiredJwtException(){
		String message = "Invalid username or password, please check your credentials";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<String> malformedJwtException(MalformedJwtException e){
		String message = e.getMessage();
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponseTest> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		ApiResponseTest response = new ApiResponseTest();
		response.setMessage(ex.getMessage());
		response.setType(ex.getClass().toString());
		response.setA(ex.hashCode());
		response.setB(ex.getCause().toString());
		return ResponseEntity.ok(response);
	}
}
