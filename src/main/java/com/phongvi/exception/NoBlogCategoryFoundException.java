package com.phongvi.exception;

public class NoBlogCategoryFoundException extends RuntimeException {
	
	public NoBlogCategoryFoundException() {
		super();
	}
	
	public NoBlogCategoryFoundException(String message) {
		super(message);
	}
}
