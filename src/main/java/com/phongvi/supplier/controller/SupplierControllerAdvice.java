package com.phongvi.supplier.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.exception.NoSupplierFoundException;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class SupplierControllerAdvice {
	
	private static Map<String, String> CONSTRAINS_I18N_MAP = Map.of(
		      "tbl_supplier_phone_key", "Số điện thoại đã được liên kết. Vui lòng thử lại.",
		      "tbl_supplier_name_key", "Tên nhà cung cấp đã được sử dụng. Vui lòng thử lại.");
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex
			) {
		var errors = new HashMap<String, Object>();
		
		errors.put("timestamp", Utils.getCurrentTimestamp());
		errors.put("status", HttpStatus.BAD_REQUEST.value());
		
		ex.getBindingResult().getAllErrors()
			.forEach(e -> {
				var fieldName = ((FieldError) e).getField();
				var errorMsg = e.getDefaultMessage();
				errors.put(fieldName, errorMsg);
			});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex
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
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex
			) {
		
		String rootMessage = getRootCause(ex).getMessage();
		
		if (rootMessage != null) {
	        String lowerCaseMsg = rootMessage.toLowerCase();
	        for (Map.Entry<String, String> entry : CONSTRAINS_I18N_MAP.entrySet()) {
	            if (lowerCaseMsg.contains(entry.getKey())) {
	                return Utils.generateMessageResponseEntity(
	        				entry.getValue(), 
	        				HttpStatus.BAD_REQUEST);
	            }
	        }
	    }
		
		return Utils.generateMessageResponseEntity(
				rootMessage, 
				HttpStatus.BAD_REQUEST);
	}
	
	@NonNull
	private static Throwable getRootCause(@NonNull Throwable t) {
		Throwable rootCause = NestedExceptionUtils.getRootCause(t);
	  	return rootCause != null ? rootCause : t;
	}
}
