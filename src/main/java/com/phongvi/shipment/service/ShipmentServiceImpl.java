package com.phongvi.shipment.service;

import org.springframework.stereotype.Service;

import com.phongvi.shipment.ShipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
	private final ShipmentRepository repository;
	private final ShipmentMappingService mappingService;
	
	
}
