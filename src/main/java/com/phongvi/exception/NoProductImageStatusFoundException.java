package com.phongvi.exception;

public class NoProductImageStatusFoundException extends RuntimeException{
	
	public NoProductImageStatusFoundException() {
		super();
	}
	
	public NoProductImageStatusFoundException(String message) {
		super(message);
	}

}
