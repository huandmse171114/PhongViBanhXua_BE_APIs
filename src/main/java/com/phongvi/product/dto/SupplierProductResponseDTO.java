package com.phongvi.product.dto;

import java.sql.Time;

import lombok.Builder;

@Builder
public record SupplierProductResponseDTO(
			Long id,
			String name,
			String ownerName,
			String phone,
			String street,
			String ward,
			String district,
			String province,
			Time openedTime,
			Time closedTime
		) {
}
