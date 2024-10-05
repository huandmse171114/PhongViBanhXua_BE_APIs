package com.phongvi.supplier.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.supplier.SupplierStatus;
import com.phongvi.supplier.service.SupplierService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Supplier", description = "Supplier Management APIs")
@RequestMapping("/store/api/v1/suppliers")
public class SupplierController {
	private final SupplierService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveSupplier(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String street,
				@RequestParam(required = false, defaultValue = "") String ward,
				@RequestParam(required = false, defaultValue = "") String district,
				@RequestParam(required = false, defaultValue = "") String province,
				@RequestParam(required = false, defaultValue = "") String name,
				@RequestParam(required = false) List<Long> categories
			) {
		
		if (categories == null) categories = new ArrayList<>();
		
		return service.getAllSupplierByStatus(SupplierStatus.ACTIVE, page, size, 
				street, ward, district, province, name, categories, false);
	}
}
