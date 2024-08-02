package com.phongvi.supplier.dto;

import java.sql.Time;
import java.util.List;

import com.phongvi.product_category.dto.ProductCategoryRelateResponse;
import lombok.Builder;

@Builder
public record SupplierResponseDTO(
			Long id,
			String name,
			String description,
			String ownerName,
			String phone,
			Time openedTime,
			Time closedTime,
			String street,
			String ward,
			String district,
			String province,
			String wardCode,
			Integer districtId,
			Integer provinceId,
			String shopSurroundingImg1,
			String shopSurroundingImg2,
			String shopSurroundingImg3,
			int totalRating,
			double averageStar,
			List<ProductCategoryRelateResponse> categories
		) {

}
