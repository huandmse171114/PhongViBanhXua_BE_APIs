package com.phongvi.supplier.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.product_category.dto.ProductCategoryResponseDTO;
import com.phongvi.supplier.SupplierStatus;

import lombok.Builder;

@Builder
public record SupplierAdminResponseDTO(
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
			SupplierStatus status,
			List<ProductCategoryResponseDTO> categories,
			Timestamp createdAt,
			String createdBy,
			Timestamp lastChangedAt,
			String lastChangedBy
		) {

}
