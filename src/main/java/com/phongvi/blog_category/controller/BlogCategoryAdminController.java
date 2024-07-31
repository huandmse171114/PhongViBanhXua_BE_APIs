package com.phongvi.blog_category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.blog_category.service.BlogCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blog Category", description = "Blog Category Management APIs")
@RequestMapping("/admin/api/v1/blog-categories")
public class BlogCategoryAdminController {
	private final BlogCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllBlogCategory() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
