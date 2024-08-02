package com.phongvi.blog_category.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phongvi.exception.NoBlogCategoryFoundException;
import com.phongvi.utils.Utils;

@RestControllerAdvice(basePackages = "com.phongvi.blog_category.controller")
public class BlogCategoryControllerAdvice {
	private static Map<String, String> CONSTRAINS_I18N_MAP = Map.of(
		      "tbl_blogcategory_name_key", "Tên danh mục blog đã tồn tại. Vui lòng thử lại.");
	
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

	@ExceptionHandler(NoBlogCategoryFoundException.class)
	public ResponseEntity<?> handleNoBlogCategoryFoundException(
			NoBlogCategoryFoundException ex
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
