package com.phongvi.shipment.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.shipment.ShipmentStatus;
import com.phongvi.shipment.dto.ShipmentCreateDTO;
import com.phongvi.shipment.dto.ShipmentUpdateDTO;

import jakarta.validation.Valid;

public interface ShipmentService {

	ResponseEntity<?> getAllShipmentByStatus(ShipmentStatus active, String username, Integer page, Integer size);

	ResponseEntity<?> getDefaultShipment(String username);

	void saveShipment(String username, @Valid ShipmentCreateDTO shipmentDTO);

	void updateShipment(String username, @Valid ShipmentUpdateDTO shipmentDTO);

	ResponseEntity<?> updateShipmentStatus(String username, Long id, ShipmentStatus status);

}
