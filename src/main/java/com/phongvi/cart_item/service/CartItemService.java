package com.phongvi.cart_item.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.cart_item.CartItemStatus;
import com.phongvi.cart_item.dto.CartItemCreateUpdateBodyDTO;

import jakarta.validation.Valid;

public interface CartItemService {

	ResponseEntity<?> getAllCartItemByStatus(CartItemStatus active, Integer page, Integer size, String username);

	String modifyCartItem(@Valid CartItemCreateUpdateBodyDTO cartItemDTO, String username);

}
