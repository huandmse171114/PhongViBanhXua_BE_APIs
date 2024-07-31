package com.phongvi.order_item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.order_item.service.OrderItemService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order Item", description = "Order Item Management APIs")
@RequestMapping("/admin/api/v1/order-items")
public class OrderItemAdminController {
	private final OrderItemService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllOrderItem() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
