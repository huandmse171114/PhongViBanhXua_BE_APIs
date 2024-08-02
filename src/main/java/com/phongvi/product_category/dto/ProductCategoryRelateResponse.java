package com.phongvi.product_category.dto;

import lombok.Builder;

@Builder
public record ProductCategoryRelateResponse(
			Long id,
			String name,
			String description
		) {

}
