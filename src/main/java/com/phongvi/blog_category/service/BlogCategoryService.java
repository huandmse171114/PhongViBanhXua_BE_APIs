package com.phongvi.blog_category.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.blog_category.dto.BlogCategoryCreateDTO;
import com.phongvi.blog_category.dto.BlogCategoryUpdateDTO;

public interface BlogCategoryService {

	ResponseEntity<?> getAllBlogCategoryByStatus(BlogCategoryStatus status, Integer page, Integer size, String name,
			boolean isAdminCalled);

	ResponseEntity<?> updateBlogCategory(Long id, BlogCategoryUpdateDTO blogCategoryDTO);

	ResponseEntity<?> saveBlogCategory(BlogCategoryCreateDTO blogCategoryDTO);

	ResponseEntity<?> updateBlogCategoryStatus(BlogCategoryStatus status, Long id);

}
 