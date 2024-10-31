package com.phongvi.combo.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.combo.ComboStatus;
import com.phongvi.combo_item.dto.ComboItemAdminResponseDTO;
import com.phongvi.combo_item.dto.ComboItemComboCreateDTO;

public record ComboCreateDTO(
			String name,
			String description,
			Long price,
			Long discountPrice,
			Date discountExpiry,
			Integer dailyStock,
			List<ComboItemComboCreateDTO> items
		) {

}
