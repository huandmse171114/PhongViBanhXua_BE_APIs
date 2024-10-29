package com.phongvi.product.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.product.ProductStatus;

import lombok.Builder;

@Builder
public record ProductResponseDTO(
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
