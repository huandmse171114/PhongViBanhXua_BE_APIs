package com.phongvi.product.dto;

import com.phongvi.product_image.ProductImageStatus;

import lombok.Builder;

@Builder
public record ProductImageProductResponseDTO(
			Long id,
			Integer index,
			String source,
			String description,
			ProductImageStatus status
		) {

}
