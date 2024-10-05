package com.phongvi.customer.service;

import org.springframework.stereotype.Service;

import com.phongvi.customer.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository repository;
	private final CustomerMappingService mappingService;
	
	
}
