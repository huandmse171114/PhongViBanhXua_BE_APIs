package com.phongvi.combo_item.dto;

import java.sql.Timestamp;

import com.phongvi.combo_item.ComboItemStatus;
import com.phongvi.product.dto.ProductComboItemAdminResponseDTO;

import lombok.Builder;

@Builder
public record ComboItemAdminResponseDTO(
			Integer quantity,
			ProductComboItemAdminResponseDTO product,
			Timestamp createdAt,
			String createdBy,
			Timestamp lastChangedAt,
			String lastChangedBy,
			ComboItemStatus status
		) {

}
