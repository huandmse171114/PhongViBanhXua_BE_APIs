package com.phongvi.exception;

public class NoSupplierFoundException extends RuntimeException {
	public NoSupplierFoundException() {
		super();
	}
	
	public NoSupplierFoundException(String message) {
		super(message);
	}
}
