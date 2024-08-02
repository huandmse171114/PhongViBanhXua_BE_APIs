package com.phongvi.supplier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.NoSupplierFoundException;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class SupplierControllerAdvice {
	
	@ExceptionHandler(NoSupplierFoundException.class)
	public ResponseEntity<?> handleNoSupplierFoundException(
			NoSupplierFoundException ex
			) {
		
		return Utils.generateMessageResponseEntity(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND);
	}
}
