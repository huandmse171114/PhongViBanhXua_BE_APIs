package com.phongvi.shipper.service;

import org.springframework.stereotype.Service;

import com.phongvi.shipper.ShipperRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {
	private final ShipperRepository repository;
	private final ShipperMappingService mappingService;
	
	
}
