package com.phongvi.exception;

public class NoProductCategoryFoundException extends RuntimeException{
	
	public NoProductCategoryFoundException() {
		super();
	}
	
	public NoProductCategoryFoundException(String message) {
		super(message);
	}
}
