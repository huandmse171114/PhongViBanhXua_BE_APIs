package com.phongvi.shipment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.shipment.service.ShipmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shipment", description = "Shipment Management APIs")
@RequestMapping("/admin/api/v1/shipments")
public class ShipmentAdminController {
	private final ShipmentService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllShipment() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
