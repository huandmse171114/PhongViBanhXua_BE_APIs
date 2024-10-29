package com.phongvi.product.dto;

import java.sql.Date;
import java.util.List;

import lombok.Builder;

@Builder
public record ProductComboItemAdminResponseDTO(
			Long id,
			String name,
			String description,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			Integer calories,
			int totalRating,
			double averageStar,
			Integer dailyStock,
			List<ProductImageProductResponseDTO> images,
			List<CategoryProductResponseDTO> categories,
			SupplierProductResponseDTO supplier
		) {

}
