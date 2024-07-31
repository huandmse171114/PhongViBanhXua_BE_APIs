package com.phongvi.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.blog.service.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blog", description = "Blog Management APIs")
@RequestMapping("/store/api/v1/blogs")
public class BlogController {
	private final BlogService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveBlog() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
