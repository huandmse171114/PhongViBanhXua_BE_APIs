package com.phongvi.shipment.dto;

import lombok.Builder;

@Builder
public record ShipmentResponseDTO(
			Long id,
			String receiverName,
			String contactNumber,
			String description,
			String street,
			String ward,
			String wardCode,
			String district,
			Integer districtId,
			boolean isDefault
		) {

}
