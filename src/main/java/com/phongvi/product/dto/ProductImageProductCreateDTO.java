package com.phongvi.product.dto;

import lombok.Builder;

@Builder
public record ProductImageProductCreateDTO(
			Integer index,
			String source,
			String description
		) {
}
