package com.phongvi.combo.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.combo.ComboStatus;
import com.phongvi.combo_item.dto.ComboItemAdminResponseDTO;

import lombok.Builder;

@Builder
public record ComboAdminResponseDTO(
			Long id,
			String name,
			String description,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			int totalRating,
			double averageStar,
			Integer dailyStock,
			ComboStatus status,
			Timestamp createdAt,
			String createdBy,
			Timestamp lastChangedAt,
			String lastChangedBy,
			List<ComboItemAdminResponseDTO> items
		) {

}
