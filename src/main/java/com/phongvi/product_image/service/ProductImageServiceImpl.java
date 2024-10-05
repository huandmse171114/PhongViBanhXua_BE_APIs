package com.phongvi.product_image.service;

import org.springframework.stereotype.Service;

import com.phongvi.product_image.ProductImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
	private final ProductImageRepository repository;
	private final ProductImageMappingService mappingService;
	
	
}
