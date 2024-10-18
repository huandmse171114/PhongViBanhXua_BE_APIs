package com.phongvi.auth.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.auth.dto.GoogleTokenDTO;
import com.phongvi.auth.dto.LoginDTO;

public interface AuthService {

	ResponseEntity<?> authenticateUser(LoginDTO loginRequest);

	ResponseEntity<?> authenticateGoogleUser(GoogleTokenDTO tokenDTO);

}
