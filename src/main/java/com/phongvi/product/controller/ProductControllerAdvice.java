package com.phongvi.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.utils.Utils;

import jakarta.transaction.NotSupportedException;

@RestControllerAdvice(basePackages = "com.phongvi.product.controller")
public class ProductControllerAdvice {
	@ExceptionHandler(NotSupportedException.class)
	public ResponseEntity<?> handleNotSupportedException(
			NotSupportedException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
}
