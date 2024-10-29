package com.phongvi.product.dto;

import java.sql.Date;
import java.util.List;

import com.phongvi.product.ProductStatus;

public record ProductUpdateDTO(
			String name,
			String description,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			Integer calories,
			Integer dailyStock,
			List<ProductImageProductUpdateDTO> images,
			List<Long> categories,
			Long supplier
		) {

}
