package com.phongvi.order.service;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItemRepository;
import com.phongvi.exception.NoShipmentFoundException;
import com.phongvi.order.Order;
import com.phongvi.order.OrderPaymentStatus;
import com.phongvi.order.OrderShippingType;
import com.phongvi.order.OrderStatus;
import com.phongvi.order.dto.OrderCreateDTO;
import com.phongvi.shipment.Shipment;
import com.phongvi.shipment.ShipmentRepository;
import com.phongvi.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderMappingService {
	private final ShipmentRepository shipmentRepository;
	
	public Order orderCreateDTOToOrder(OrderCreateDTO orderDTO) {
		
		if (orderDTO.shippingType() == OrderShippingType.SHIPPING) {
			
			Optional<Shipment> shipmentOptional = shipmentRepository.findById(orderDTO.shipmentId());
			
			if (shipmentOptional.isEmpty()) throw new NoShipmentFoundException("Không tìm thấy thông tin giao hàng.");
			
			Shipment shipment = shipmentOptional.get();
			
			return Order.builder()
					.expectedDeliveryTime(null) //no calculating method yet
					.district(shipment.getDistrict())
					.districtId(shipment.getDistrictId())
					.ward(shipment.getWard())
					.wardCode(shipment.getWardCode())
					.province(shipment.getProvince())
					.provinceId(shipment.getProvinceId())
					.street(shipment.getStreet())
					.shippingFee(0L) // no shipping fee
					.contactNumber(shipment.getContactNumber())
					.receiverName(shipment.getReceiverName())
					.status(OrderStatus.CREATED)
					.paymentType(orderDTO.paymentType())
					.paymentStatus(OrderPaymentStatus.PENDING)
					.shippingType(orderDTO.shippingType())
					.createdAt(Utils.getCurrentTimestamp())
					.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.lastChangedAt(Utils.getCurrentTimestamp())
					.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.build();
		} else {
			return Order.builder()
					.expectedDeliveryTime(null) //no expected time if customer take by themselves
					.shippingFee(0L) // no shipping fee
					.contactNumber(orderDTO.contactPhone())
					.receiverName(orderDTO.receiverName())
					.status(OrderStatus.CREATED)
					.paymentType(orderDTO.paymentType())
					.paymentStatus(OrderPaymentStatus.PENDING)
					.shippingType(orderDTO.shippingType())
					.createdAt(Utils.getCurrentTimestamp())
					.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.lastChangedAt(Utils.getCurrentTimestamp())
					.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.build();
		}
		
	}

}
