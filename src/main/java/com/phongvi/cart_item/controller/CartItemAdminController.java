package com.phongvi.cart_item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.cart_item.service.CartItemService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cart Item", description = "Cart Item Management APIs")
@RequestMapping("/admin/api/v1/cart-items")
public class CartItemAdminController {
	private final CartItemService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCartItem() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
