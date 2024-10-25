package com.phongvi.cart_item.dto;

import com.phongvi.cart_item.CartItemType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CartItemCreateUpdateBodyDTO(
			Long productId,
			Long comboId,
			CartItemType type,
			@NotNull(message = "Không được để trống số lượng sản phẩm")
			Integer quantity
		) {
	
}
