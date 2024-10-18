package com.phongvi.exception;

public class NoBlogFoundException extends RuntimeException {
	
	public NoBlogFoundException() {
		super();
	}
	
	public NoBlogFoundException(String message) {
		super(message);
	}
}
