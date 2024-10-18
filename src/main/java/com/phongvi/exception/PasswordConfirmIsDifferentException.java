package com.phongvi.exception;

public class PasswordConfirmIsDifferentException extends RuntimeException {
	public PasswordConfirmIsDifferentException() {
		super();
	}
	
	public PasswordConfirmIsDifferentException(String message) {
		super(message);
	}
}
