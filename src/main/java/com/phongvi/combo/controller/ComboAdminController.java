package com.phongvi.combo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.combo.ComboStatus;
import com.phongvi.combo.dto.ComboCreateDTO;
import com.phongvi.combo.service.ComboService;
import com.phongvi.product.ProductStatus;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Combo", description = "Combo Management APIs")
@RequestMapping("/admin/api/v1/combos")
public class ComboAdminController {
	private final ComboService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCombo(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@RequestParam(required = false, defaultValue = "") String name,
				@RequestParam(required = false) ComboStatus status
			) {
		return service.getAllComboByStatus(status, page, size, name, true);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveCombo(
				@Valid @RequestBody ComboCreateDTO comboDTO
			) {
		return service.saveCombo(comboDTO);
	}

}
