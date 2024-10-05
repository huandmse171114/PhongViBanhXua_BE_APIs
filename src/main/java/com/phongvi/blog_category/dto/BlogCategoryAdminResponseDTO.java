package com.phongvi.blog_category.dto;

import java.sql.Timestamp;

import com.phongvi.blog_category.BlogCategoryStatus;

import lombok.Builder;

@Builder
public record BlogCategoryAdminResponseDTO(
			Long id,
			String name,
			String description,
			BlogCategoryStatus status,
			Timestamp createdAt,
			String createdBy,
			Timestamp lastChangedAt,
			String lastChangedBy
		) {

}
