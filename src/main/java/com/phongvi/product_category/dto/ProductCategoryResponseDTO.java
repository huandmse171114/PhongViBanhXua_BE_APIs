package com.phongvi.product_category.dto;

import lombok.Builder;

@Builder
public record ProductCategoryResponseDTO(
			Long id,
			Long parentId,
			String parentName,
			String name,
			String description,
			String categoryImg
		) {

}
