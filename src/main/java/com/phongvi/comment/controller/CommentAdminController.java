package com.phongvi.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.comment.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment Management APIs")
@RequestMapping("/admin/api/v1/comments")
public class CommentAdminController {
	private final CommentService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllComment() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
