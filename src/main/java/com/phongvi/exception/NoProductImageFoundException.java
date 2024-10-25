package com.phongvi.exception;

public class NoProductImageFoundException extends RuntimeException{
	
	public NoProductImageFoundException() {
		super();
	}
	
	public NoProductImageFoundException(String message) {
		super(message);
	}

}
