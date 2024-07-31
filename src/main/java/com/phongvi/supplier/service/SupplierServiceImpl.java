package com.phongvi.supplier.service;

import org.springframework.stereotype.Service;

import com.phongvi.supplier.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
	private final SupplierMappingService mappingService;
	private final SupplierRepository repository;
	
	
}
