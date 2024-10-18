package com.phongvi.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.auth.dto.GoogleTokenDTO;
import com.phongvi.auth.dto.LoginDTO;
import com.phongvi.auth.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication Management APIs")
@RequestMapping("/store/api/v1/auth")
public class AuthController {
	private final AuthService service;
	
	@PostMapping("/oauth2/google")
	public ResponseEntity<?> authenticateGoogleUser(@Valid @RequestBody GoogleTokenDTO tokenDTO) {
		return service.authenticateGoogleUser(tokenDTO);
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
		return service.authenticateUser(loginRequest);
	}
}
