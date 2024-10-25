package com.phongvi.product.dto;

import com.phongvi.product_image.ProductImageStatus;

public record ProductImageProductUpdateDTO(
			Long id,
			Integer index,
			String source,
			String description,
			ProductImageStatus status
		) {

}
