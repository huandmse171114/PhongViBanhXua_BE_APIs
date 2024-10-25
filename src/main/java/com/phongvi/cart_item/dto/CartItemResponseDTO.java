package com.phongvi.cart_item.dto;

import com.phongvi.cart_item.CartItemType;

import lombok.Builder;

@Builder
public record CartItemResponseDTO(
			Long id,
			Integer quantity,
			CartItemType type,
			CartItemProductResponse product,
			CartItemComboResponse combo
		) {

}
