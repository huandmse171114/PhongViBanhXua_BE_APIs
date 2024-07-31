package com.phongvi.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.order.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order Management APIs")
@RequestMapping("/admin/api/v1/orders")
public class OrderAdminController {
	private final OrderService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllOrder() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
