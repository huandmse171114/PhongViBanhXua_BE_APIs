package com.phongvi.cart_item.dto;

import java.sql.Date;

import lombok.Builder;

@Builder
public record CartItemProductResponse(
			Long id,
			String name,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			Integer calories,
			String firstImageUrl
		) {

}
