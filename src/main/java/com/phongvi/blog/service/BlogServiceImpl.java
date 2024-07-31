package com.phongvi.blog.service;

import org.springframework.stereotype.Service;

import com.phongvi.blog.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
	private final BlogRepository repository;
	private final BlogMappingService mappingService;
	
	
}
