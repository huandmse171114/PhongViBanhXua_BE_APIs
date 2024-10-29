package com.phongvi.order.dto;

import java.util.List;

import com.phongvi.order.OrderPaymentStatus;
import com.phongvi.order.OrderPaymentType;
import com.phongvi.order.OrderShippingType;

public record OrderCreateDTO(
			Long shipmentId,
			String receiverName,
			String contactPhone,
			List<Long> items,
			String redirectUrl,
			OrderPaymentType paymentType,
			OrderShippingType shippingType
		) {

}
