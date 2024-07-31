package com.phongvi.product_category.service;

import org.springframework.stereotype.Service;

import com.phongvi.product_category.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
	private final ProductCategoryRepository repository;
	private final ProductCategoryMappingService mappingService;
}
