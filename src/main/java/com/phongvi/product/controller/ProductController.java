package com.phongvi.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.product.ProductStatus;
import com.phongvi.product.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product Management APIs")
@RequestMapping("/store/api/v1/products")
public class ProductController {
	private final ProductService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveProduct(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name,
				@RequestParam(required = false) List<Long> categories,
				@RequestParam(required = false, defaultValue = "") String supplierName
			) {
		return service.getAllProductByStatus(ProductStatus.ACTIVE, page, size, name, categories, supplierName, false);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getActiveProductById(@PathVariable("id") Long id) {
		return service.getProductByIdAndStatus(ProductStatus.ACTIVE, id);
	}
}
