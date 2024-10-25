package com.phongvi.exception;

public class NoProductFoundException extends RuntimeException{
	
	public NoProductFoundException() {
		super();
	}
	
	public NoProductFoundException(String message) {
		super(message);
	}

}
