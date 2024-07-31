package com.phongvi.combo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.combo.service.ComboService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Combo", description = "Combo Management APIs")
@RequestMapping("/admin/api/v1/combos")
public class ComboAdminController {
	private final ComboService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCombo() {
		return new ResponseEntity<>("Not Supported yet!", HttpStatus.OK);
	}

}
