package com.phongvi.cart_item.dto;

import java.sql.Date;
import java.util.List;

import lombok.Builder;

@Builder
public record CartItemComboResponse(
			Long id,
			String name,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			List<CartItemComboItemResponse> items
		) {

}
