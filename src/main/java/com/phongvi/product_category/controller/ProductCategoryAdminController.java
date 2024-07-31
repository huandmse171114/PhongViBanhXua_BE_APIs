package com.phongvi.product_category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.product_category.service.ProductCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product Category", description = "Product Category Management APIs")
@RequestMapping("/admin/api/v1/product-categories")
public class ProductCategoryAdminController {
	private final ProductCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllProductCategory() {	
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
