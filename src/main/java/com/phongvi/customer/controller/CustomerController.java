package com.phongvi.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer Management APIs")
@RequestMapping("/store/api/v1/customers")
public class CustomerController {
	private final CustomerService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveCustomer() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
