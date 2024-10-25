package com.phongvi.combo.service;

import org.springframework.stereotype.Service;

import com.phongvi.cart_item.dto.CartItemComboResponse;
import com.phongvi.combo.Combo;
import com.phongvi.combo_item.service.ComboItemMappingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboMappingService {
	
	private ComboItemMappingService itemMappingService;
	
	public CartItemComboResponse comboToCartItemComboResponse(Combo combo) {
		return CartItemComboResponse.builder()
				.id(combo.getId())
				.name(combo.getName())
				.price(combo.getPrice())
				.discountExpiry(combo.getDiscountExpiry())
				.discountPercent(combo.getDiscountPercent())
				.items(combo.getItems() != null && !combo.getItems().isEmpty()
						? combo.getItems().stream()
								.map(item -> itemMappingService.comboItemToCartItemComboItemResponse(item))
								.toList()
						: null)
				.build();
	}

}
