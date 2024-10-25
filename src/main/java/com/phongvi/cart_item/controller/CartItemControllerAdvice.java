package com.phongvi.cart_item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.NoCartItemFoundException;
import com.phongvi.exception.NoComboFoundException;
import com.phongvi.exception.NoCustomerFoundException;
import com.phongvi.exception.NoProductFoundException;
import com.phongvi.exception.NoUserFoundException;
import com.phongvi.utils.Utils;

@RestControllerAdvice(basePackages = "com.phongvi.cart_item.controller")
public class CartItemControllerAdvice {
	
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<?> handleNoProductFoundException(
			NoProductFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoComboFoundException.class)
	public ResponseEntity<?> handleNoComboFoundException(
			NoComboFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoCartItemFoundException.class)
	public ResponseEntity<?> handleNoCartItemFoundException(
			NoCartItemFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<?> handleNoUserFoundException(
			NoUserFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoCustomerFoundException.class)
	public ResponseEntity<?> handleNoCustomerFoundException(
			NoCustomerFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
}
