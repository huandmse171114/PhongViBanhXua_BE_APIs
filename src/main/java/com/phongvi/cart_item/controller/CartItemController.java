package com.phongvi.cart_item.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemStatus;
import com.phongvi.cart_item.dto.CartItemCreateUpdateBodyDTO;
import com.phongvi.cart_item.service.CartItemService;
import com.phongvi.utils.Utils;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cart Item", description = "Cart Item Management APIs")
@RequestMapping("/store/api/v1/cart-items")
public class CartItemController {
	private final CartItemService service;
	
	@GetMapping("/customers/{username}/cart-items")
	public ResponseEntity<?> getAllActiveCartDetail(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size,
				@PathVariable("username") String username
			) {
		return service.getAllCartItemByStatus(CartItemStatus.ACTIVE, page, size, username);
	}
	
	@PostMapping("/customers/{username}/cart-details")
	public ResponseEntity<?> modifyCartDetails(@Valid @RequestBody CartItemCreateUpdateBodyDTO cartItemDTO,
			@PathVariable("username") String username) {
		String message = service.modifyCartItem(cartItemDTO, username);
		
		return Utils.generateMessageResponseEntity(
				message, 
				HttpStatus.OK);
	}
}
