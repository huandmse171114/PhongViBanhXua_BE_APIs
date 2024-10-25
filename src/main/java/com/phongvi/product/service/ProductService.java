package com.phongvi.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.phongvi.product.ProductStatus;
import com.phongvi.product.dto.ProductCreateDTO;
import com.phongvi.product.dto.ProductUpdateDTO;

import jakarta.validation.Valid;

public interface ProductService {

	ResponseEntity<?> getAllProductByStatus(ProductStatus status, Integer page, Integer size, String name,
			List<Long> categories, String supplierName, boolean isAdminCalled);

	ResponseEntity<?> updateProductStatus(ProductStatus status, Long id);

	ResponseEntity<?> saveProduct(ProductCreateDTO productDTO);

	ResponseEntity<?> updateProduct(Long id, ProductUpdateDTO productDTO);

	ResponseEntity<?> getProductByIdAndStatus(ProductStatus status, Long id);
}
