package com.phongvi.combo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.combo.ComboStatus;
import com.phongvi.combo.service.ComboService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Combo", description = "Combo Management APIs")
@RequestMapping("/store/api/v1/combos")
public class ComboController {
	private final ComboService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveCombo(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name
			) {
		return service.getAllComboByStatus(ComboStatus.ACTIVE, page, size, name, false);
	}

}
