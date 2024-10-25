package com.phongvi.exception;

public class NoCartItemFoundException extends RuntimeException{
	
	public NoCartItemFoundException() {
		super();
	}
	
	public NoCartItemFoundException(String message) {
		super(message);
	}

}
