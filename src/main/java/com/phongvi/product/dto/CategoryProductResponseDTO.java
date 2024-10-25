package com.phongvi.product.dto;

import com.phongvi.product_category.ProductCategoryStatus;

import lombok.Builder;

@Builder
public record CategoryProductResponseDTO(
			Long id,
			String name,
			String description,
			String categoryImg,
			ProductCategoryStatus status
		) {

}
