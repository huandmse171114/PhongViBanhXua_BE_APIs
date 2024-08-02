package com.phongvi.blog_category.dto;

import lombok.Builder;

@Builder
public record BlogCategoryResponseDTO(
			Long id,
			String name,
			String description
		) {

}
