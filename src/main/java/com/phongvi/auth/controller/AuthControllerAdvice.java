package com.phongvi.auth.controller;

import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.utils.Utils;

import io.jsonwebtoken.io.IOException;

@RestControllerAdvice(basePackages = "com.phongvi.auth.controller")
public class AuthControllerAdvice {
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException(
			AuthenticationException ex
			){
		return Utils.generateMessageResponseEntity(
				"Tài khoản hoặc mật khẩu không đúng.", 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(
			IllegalArgumentException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GeneralSecurityException.class)
	public ResponseEntity<?> handleGeneralSecurityException(
			GeneralSecurityException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException(
			IOException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
}
