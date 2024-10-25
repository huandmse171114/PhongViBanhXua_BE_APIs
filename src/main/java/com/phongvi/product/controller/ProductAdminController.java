package com.phongvi.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.product.ProductStatus;
import com.phongvi.product.dto.ProductCreateDTO;
import com.phongvi.product.dto.ProductUpdateDTO;
import com.phongvi.product.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product Management APIs")
@RequestMapping("/admin/api/v1/products")
public class ProductAdminController {
	private final ProductService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllProduct(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false) List<Long> categories,
			@RequestParam(required = false, defaultValue = "") String supplierName,
			@RequestParam(required = false) ProductStatus status
		) {
		
		return service.getAllProductByStatus(status, page, size, name, categories, supplierName, true);

	}
	
	@PutMapping("{id}/activate")
	public ResponseEntity<?> activateProduct(@PathVariable("id") Long id) {
		return service.updateProductStatus(ProductStatus.ACTIVE, id);
	}
	
	@PutMapping("{id}/deactivate")
	public ResponseEntity<?> deactivateProduct(@PathVariable("id") Long id) {
		return service.updateProductStatus(ProductStatus.INACTIVE, id);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveProduct(
				@Valid @RequestBody ProductCreateDTO productDTO
			) {
		return service.saveProduct(productDTO);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProductById(@PathVariable("id") Long id,
				@Valid @RequestBody ProductUpdateDTO productDTO
			) {
		return service.updateProduct(id,  productDTO);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
		return service.getProductByIdAndStatus(null, id);
	}
	
}
