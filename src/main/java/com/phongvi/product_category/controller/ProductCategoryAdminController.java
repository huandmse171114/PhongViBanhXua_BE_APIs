package com.phongvi.product_category.controller;

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

import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryCreateDTO;
import com.phongvi.product_category.dto.ProductCategoryUpdateDTO;
import com.phongvi.product_category.service.ProductCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product Category", description = "Product Category Management APIs")
@RequestMapping("/admin/api/v1/product-categories")
public class ProductCategoryAdminController {
	private final ProductCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllProductCategory(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name,
				@RequestParam(required = false) ProductCategoryStatus status
			) {	
		
		return service.getAllProductCategoryByStatus(status, page, size, name, true);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductCategoryById(@PathVariable("id") Long id) {
		return service.getProductCategoryByIdAndStatus(null, id, true);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductCategoryById(@PathVariable("id") Long id,
			@Valid @RequestBody ProductCategoryUpdateDTO productCategoryDto) {
		return service.updateProductCategory(id, productCategoryDto);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveProductCategory(
			@Valid @RequestBody ProductCategoryCreateDTO productCategoryDto) {
		return service.saveProductCategory(productCategoryDto);
	}
	
	@DeleteMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateProductCategory(@PathVariable("id") Long id) {
		return service.updateProductCategoryStatus(ProductCategoryStatus.INACTIVE, id);
	}
	
	@PutMapping("/{id}/activate")
	public ResponseEntity<?> activateProductCategory(@PathVariable("id") Long id) {
		return service.updateProductCategoryStatus(ProductCategoryStatus.ACTIVE, id);
	}
}
