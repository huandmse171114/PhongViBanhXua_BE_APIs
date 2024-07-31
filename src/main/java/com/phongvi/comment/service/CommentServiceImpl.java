package com.phongvi.comment.service;

import org.springframework.stereotype.Service;

import com.phongvi.comment.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentRepository repository;
	private final CommentMappingService mappingService;
	
	
}
