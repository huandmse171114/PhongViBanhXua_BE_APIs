package com.phongvi.supplier.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.supplier.SupplierStatus;
import com.phongvi.supplier.dto.SupplierCreateDTO;
import com.phongvi.supplier.dto.SupplierUpdateDTO;
import com.phongvi.supplier.service.SupplierService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Supplier", description = "Supplier Management APIs")
@RequestMapping("/admin/api/v1/suppliers")
public class SupplierAdminController {
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
				@RequestParam(required = false) List<Long> categories,
				@RequestParam(required = false) SupplierStatus status
			) {
		
		if (categories == null) categories = new ArrayList<>();
		
		return service.getAllSupplierByStatus(status, page, size, 
				street, ward, district, province, name, categories, true);
	}
	
	@PutMapping("/{id}/activate")
	public ResponseEntity<?> activateSupplier(@PathVariable("id") Long id) {
		return service.updateSupplierStatus(SupplierStatus.ACTIVE, id);
	}
	
	@DeleteMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateSupplier(@PathVariable("id") Long id) {
		return service.updateSupplierStatus(SupplierStatus.INACTIVE, id);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveSupplier(@Valid @RequestBody SupplierCreateDTO supplierDTO) {
		return service.saveSupplier(supplierDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @Valid @RequestBody SupplierUpdateDTO supplierDTO) {
		return service.updateSupplier(id, supplierDTO);
	}
}
