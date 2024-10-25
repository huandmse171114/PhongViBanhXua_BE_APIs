package com.phongvi.product.dto;

import java.sql.Date;
import java.util.List;

import com.phongvi.product.ProductStatus;

public record ProductCreateDTO(
			String name,
			String description,
			Long price,
			Integer discountPercent,
			Date discountExpiry,
			Integer calories,
			Integer dailyStock,
			List<ProductImageProductCreateDTO> images,
			List<Long> categories,
			Long supplier
		) {

}
