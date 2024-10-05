package com.phongvi.blog_category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.blog_category.dto.BlogCategoryCreateDTO;
import com.phongvi.blog_category.dto.BlogCategoryUpdateDTO;
import com.phongvi.blog_category.service.BlogCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blog Category", description = "Blog Category Management APIs")
@RequestMapping("/admin/api/v1/blog-categories")
public class BlogCategoryAdminController {
	private final BlogCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllBlogCategory(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name,
				@RequestParam(required = false) BlogCategoryStatus status
			) {
		return service.getAllBlogCategoryByStatus(status, page, size, name, true);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBlogCategoryById(@PathVariable("id") Long id,
			@Valid @RequestBody BlogCategoryUpdateDTO blogCategoryDTO) {
		return service.updateBlogCategory(id, blogCategoryDTO);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveBlogCategory(@Valid @RequestBody BlogCategoryCreateDTO blogCategoryDTO) {
		return service.saveBlogCategory(blogCategoryDTO);
	}
	
	@DeleteMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateBlogCategory(@PathVariable("id") Long id) {
		return service.updateBlogCategoryStatus(BlogCategoryStatus.INACTIVE, id);
	}
	
	@PutMapping("/{id}/activate")
	public ResponseEntity<?> activateBlogCategory(@PathVariable("id") Long id) {
		return service.updateBlogCategoryStatus(BlogCategoryStatus.ACTIVE, id);
	}
}
