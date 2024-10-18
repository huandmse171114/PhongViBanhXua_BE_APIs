package com.phongvi.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.blog.BlogStatus;
import com.phongvi.blog.service.BlogService;
import com.phongvi.blog_category.BlogCategory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blog", description = "Blog Management APIs")
@RequestMapping("/store/api/v1/blogs")
public class BlogController {
	private final BlogService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveBlog(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String title,
				@RequestParam(required = false, defaultValue = "2024-01-01") String fromDate,
				@RequestParam(required = false, defaultValue = "2024-12-24") String toDate,
				@RequestParam(required = false, defaultValue = "") String author,
				@RequestParam(required = false) Long categoryId
			) {
		return service.getAllBlogByStatus(BlogStatus.ACTIVE, page, size, title, fromDate, toDate, author, categoryId, false);
	}
}
