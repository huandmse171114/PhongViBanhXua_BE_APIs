package com.phongvi.blog_category.service;

import org.springframework.stereotype.Service;

import com.phongvi.blog_category.BlogCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogCategoryServiceImpl implements BlogCategoryService {
	private final BlogCategoryRepository repository;
	private final BlogCategoryMappingService mappingService;
	
	
}
