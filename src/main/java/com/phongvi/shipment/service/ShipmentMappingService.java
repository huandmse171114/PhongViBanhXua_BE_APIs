package com.phongvi.shipment.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.shipment.Shipment;
import com.phongvi.shipment.ShipmentStatus;
import com.phongvi.shipment.dto.ShipmentCreateDTO;
import com.phongvi.shipment.dto.ShipmentResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentMappingService {
	
	public Shipment shipmentCreateDTOToShipment(ShipmentCreateDTO shipmentDTO) {
		return Shipment.builder()
				.receiverName(shipmentDTO.receiverName())
				.street(shipmentDTO.street())
				.ward(shipmentDTO.ward())
				.district(shipmentDTO.district())
				.wardCode(shipmentDTO.wardCode())
				.districtId(shipmentDTO.districtId())
				.contactNumber(shipmentDTO.contactNumber())
				.description(shipmentDTO.description())
				.isDefault(shipmentDTO.isDefault())
				.province("Ho Chi Minh")
				.provinceId(1293483)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("")
				.status(ShipmentStatus.ACTIVE)
				.build();
	}
	
	public ShipmentResponseDTO shipmentToShipmentResponseDTO(Shipment shipment) {
		return ShipmentResponseDTO.builder()
				.id(shipment.getId())
				.receiverName(shipment.getReceiverName())
				.contactNumber(shipment.getContactNumber())
				.description(shipment.getDescription())
				.street(shipment.getStreet())
				.ward(shipment.getWard())
				.wardCode(shipment.getWardCode())
				.district(shipment.getDistrict())
				.districtId(shipment.getDistrictId())
				.isDefault(shipment.isDefault())
				.build();
	}
}
