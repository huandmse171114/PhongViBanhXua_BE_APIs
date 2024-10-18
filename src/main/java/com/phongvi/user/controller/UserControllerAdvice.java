package com.phongvi.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.PasswordConfirmIsDifferentException;
import com.phongvi.utils.Utils;

@RestControllerAdvice(basePackages = "com.phongvi.user.controller")
public class UserControllerAdvice {
	
	@ExceptionHandler(PasswordConfirmIsDifferentException.class)
	public ResponseEntity<?> handlePasswordConfirmIsDifferentException(
				PasswordConfirmIsDifferentException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
}
