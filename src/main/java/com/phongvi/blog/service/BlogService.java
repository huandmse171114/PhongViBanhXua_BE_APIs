package com.phongvi.blog.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.blog.BlogStatus;

public interface BlogService {

	ResponseEntity<?> getAllBlogByStatus(BlogStatus status, Integer page, Integer size, String title, String fromDateStr,
			String toDateStr, String author, Long categoryId, boolean isAdminCalled);

}
