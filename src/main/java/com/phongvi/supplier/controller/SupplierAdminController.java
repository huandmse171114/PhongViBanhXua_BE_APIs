package com.phongvi.supplier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.supplier.service.SupplierService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Supplier", description = "Supplier Management APIs")
@RequestMapping("/admin/api/v1/suppliers")
public class SupplierAdminController {
	private final SupplierService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllSupplier() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
