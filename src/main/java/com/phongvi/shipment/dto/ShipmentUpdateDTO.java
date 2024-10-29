package com.phongvi.shipment.dto;

import com.phongvi.shipment.ShipmentStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ShipmentUpdateDTO(
			@NotNull(message = "Vui lòng không để trống mã địa chỉ vận chuyển.")
			Long id,
			@NotEmpty(message = "Vui lòng không để trống tên người nhận.")
			String receiverName,
			@NotEmpty(message = "Vui lòng không để trống số điện thoại liên hệ.")
			String contactNumber,
			String description,
			@NotEmpty(message = "Vui lòng không để trống địa chỉ nhà và tên đường.")
			String street,
			@NotEmpty(message = "Vui lòng không để trống thông tin phường/xã.")
			String ward,
			@NotNull
			String wardCode,
			@NotEmpty(message = "Vui lòng không để trống thông tin quận/huyện.")
			String district,
			@NotNull
			Integer districtId,
			boolean isDefault,
			ShipmentStatus status
		) {

}