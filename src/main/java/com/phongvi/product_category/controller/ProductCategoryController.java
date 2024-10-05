package com.phongvi.product_category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.service.ProductCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product Category", description = "Product Category Management APIs")
@RequestMapping("/store/api/v1/product-categories")
public class ProductCategoryController {
	private final ProductCategoryService service;

	@GetMapping("")
	public ResponseEntity<?> getAllActiveProductCategory(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name
			) {	
		
		return service.getAllProductCategoryByStatus(ProductCategoryStatus.ACTIVE, page, size, name, false);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getActiveProductCategoryById(@PathVariable("id") Long id) {
		return service.getProductCategoryByIdAndStatus(ProductCategoryStatus.ACTIVE, id, false);
	}
	
	@GetMapping("/{id}/children")
	public ResponseEntity<?> getAllProductCategoryChildrenById(
			@PathVariable("id") Long id,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return service.getAllProductCategoryChildrenByIdAndStatus(ProductCategoryStatus.ACTIVE, id, page, size);
	}
}
