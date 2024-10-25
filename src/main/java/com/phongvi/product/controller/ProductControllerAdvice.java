package com.phongvi.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.NoBlogCategoryFoundException;
import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.exception.NoProductFoundException;
import com.phongvi.exception.NoProductImageFoundException;
import com.phongvi.exception.NoProductImageStatusFoundException;
import com.phongvi.exception.NoSupplierFoundException;
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
	
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<?> handleNoProductFoundException(
			NoProductFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoProductCategoryFoundException.class)
	public ResponseEntity<?> handleNoProductCategoryFoundException(
			NoProductCategoryFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSupplierFoundException.class)
	public ResponseEntity<?> handleNoSupplierFoundException(
			NoSupplierFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoProductImageFoundException.class)
	public ResponseEntity<?> handleNoProductImageFoundException(
			NoProductImageFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoProductImageStatusFoundException.class)
	public ResponseEntity<?> handleNoProductImageStatusFoundException(
			NoProductImageStatusFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.BAD_REQUEST);
	}
}
