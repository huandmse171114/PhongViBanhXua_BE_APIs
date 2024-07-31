package com.phongvi.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> getAllActiveProduct() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
