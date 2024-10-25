package com.phongvi.cart_item.dto;

import java.sql.Date;

import lombok.Builder;

@Builder
public record CartItemProductResponse(
			Long id,
			String name,
			Long price,
			Integer discountPercent,
			Date discountExpiry,
			Integer calories,
			String firstImageUrl
		) {

}
