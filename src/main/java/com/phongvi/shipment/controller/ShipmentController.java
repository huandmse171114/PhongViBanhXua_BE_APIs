package com.phongvi.shipment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.shipment.ShipmentStatus;
import com.phongvi.shipment.dto.ShipmentCreateDTO;
import com.phongvi.shipment.dto.ShipmentUpdateDTO;
import com.phongvi.shipment.service.ShipmentService;
import com.phongvi.utils.Utils;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shipment", description = "Shipment Management APIs")
@RequestMapping("/store/api/v1/shipments")
public class ShipmentController {
	private final ShipmentService service;
	
	@GetMapping("/customers/{username}")
	public ResponseEntity<?> getAllActiveShipment(
				@PathVariable("username") String username,
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size
			) {
		return service.getAllShipmentByStatus(ShipmentStatus.ACTIVE, username, page, size);
	}
	
	@GetMapping("/customers/{username}/default")
	public ResponseEntity<?> getDefaultShipment(
				@PathVariable("username") String username
			) {
		return service.getDefaultShipment(username);
	}
	
	@PostMapping("/customers/{username}")
	public ResponseEntity<?> saveShipment(
				@PathVariable("username") String username,
				@Valid @RequestBody ShipmentCreateDTO shipmentDTO
			) {
		service.saveShipment(username, shipmentDTO);
		
		return Utils.generateMessageResponseEntity("Tạo mới thông tin giao hàng thành công", HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{username}")
	public ResponseEntity<?> updateShipment(
				@PathVariable("username") String username,
				@Valid @RequestBody ShipmentUpdateDTO shipmentDTO
			) {
		service.updateShipment(username, shipmentDTO);
		
		return Utils.generateMessageResponseEntity("Cập nhật thông tin giao hàng thành công", HttpStatus.OK);
	}
	
	@PutMapping("/{id}/customers/{username}/deactivate")
	public ResponseEntity<?> deactivateShipment(
				@PathVariable("id") Long id,
				@PathVariable("username") String username
			) {
		return service.updateShipmentStatus(username, id, ShipmentStatus.INACTIVE);
	}
	
	@PutMapping("/{id}/customers/{username}/activate")
	public ResponseEntity<?> activateShipment(
				@PathVariable("id") Long id,
				@PathVariable("username") String username
			) {
		return service.updateShipmentStatus(username, id, ShipmentStatus.ACTIVE);
	}
}

