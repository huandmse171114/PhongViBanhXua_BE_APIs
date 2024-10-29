package com.phongvi.combo_item.dto;

import com.phongvi.product.dto.ProductComboItemAdminResponseDTO;

import lombok.Builder;

@Builder
public record ComboItemResponseDTO(
			Integer quantity,
			ProductComboItemAdminResponseDTO product
		) {

}
