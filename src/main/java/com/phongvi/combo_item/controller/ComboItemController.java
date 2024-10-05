package com.phongvi.combo_item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.combo_item.service.ComboItemService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Combo Item", description = "Combo Item Management APIs")
@RequestMapping("/store/api/v1/combo-items")
public class ComboItemController {
	private final ComboItemService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveComboItem() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
