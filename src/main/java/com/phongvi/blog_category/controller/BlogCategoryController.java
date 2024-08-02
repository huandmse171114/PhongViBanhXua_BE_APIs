package com.phongvi.blog_category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.blog_category.service.BlogCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blog Category", description = "Blog Category Management APIs")
@RequestMapping("/store/api/v1/blog-categories")
public class BlogCategoryController {
	private final BlogCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveBlogCategory(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name
			) {
		return service.getAllBlogCategoryByStatus(BlogCategoryStatus.ACTIVE, page, size, name, false);
	}
}
