package com.phongvi.shipper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.shipper.service.ShipperService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shipper", description = "Shipper Management APIs")
@RequestMapping("/store/api/v1/shippers")
public class ShipperController {
	private final ShipperService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveShipper() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
