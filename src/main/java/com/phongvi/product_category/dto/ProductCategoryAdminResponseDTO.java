package com.phongvi.product_category.dto;

import java.sql.Timestamp;

import com.phongvi.product_category.ProductCategoryStatus;

import lombok.Builder;

@Builder
public record ProductCategoryAdminResponseDTO(
			Long id,
			Long parentId,
			String parentName,
			String name,
			String description,
			String categoryImg,
			ProductCategoryStatus status,
			Timestamp createdAt,
			String createdBy,
			Timestamp lastChangedAt,
			String lastChangedBy
		) {

}
