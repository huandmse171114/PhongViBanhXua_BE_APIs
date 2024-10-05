package com.phongvi.product_category.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryCreateDTO;
import com.phongvi.product_category.dto.ProductCategoryUpdateDTO;

public interface ProductCategoryService {

	ResponseEntity<?> getAllProductCategoryByStatus(ProductCategoryStatus active, 
			Integer page, 
			Integer size,
			String name,
			boolean isAdminCalled);

	ResponseEntity<?> getProductCategoryByIdAndStatus(ProductCategoryStatus status, Long id, boolean isAdminCalled);

	ResponseEntity<?> updateProductCategory(Long id, ProductCategoryUpdateDTO productCategoryDto);

	ResponseEntity<?> saveProductCategory(ProductCategoryCreateDTO productCategoryDto);

	ResponseEntity<?> updateProductCategoryStatus(ProductCategoryStatus status, Long id);

	ResponseEntity<?> getAllProductCategoryChildrenByIdAndStatus(ProductCategoryStatus status, Long id, Integer page, Integer size);

}
