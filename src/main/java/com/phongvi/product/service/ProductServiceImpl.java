package com.phongvi.product.service;

import org.springframework.stereotype.Service;

import com.phongvi.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	private final ProductMappingService mappingService;
	
	
}
