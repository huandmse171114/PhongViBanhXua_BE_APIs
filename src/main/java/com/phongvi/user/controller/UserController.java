package com.phongvi.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.user.dto.UserCreateDTO;
import com.phongvi.user.service.UserService;
import com.phongvi.utils.Utils;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User Management APIs")
@RequestMapping("/store/api/v1/users")
public class UserController {
	private final UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserCreateDTO userDTO) {
		service.registerUser(userDTO);
		
		return Utils.generateMessageResponseEntity("Đăng ký người dùng mới thành công!", HttpStatus.CREATED);
	}
	
}
