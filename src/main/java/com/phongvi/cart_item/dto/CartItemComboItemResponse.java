package com.phongvi.cart_item.dto;

import lombok.Builder;

@Builder
public record CartItemComboItemResponse(
			Long id,
			Integer quantity,
			CartItemProductResponse product
		) {

}
