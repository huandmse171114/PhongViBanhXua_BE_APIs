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
@RequestMapping("/admin/api/v1/customers")
public class CustomerAdminController {
	private final CustomerService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCustomer() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
