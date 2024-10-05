package com.phongvi.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.user.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User Management APIs")
@RequestMapping("/admin/api/v1/users")
public class UserAdminController {
	private final UserService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllUser() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
	
}
