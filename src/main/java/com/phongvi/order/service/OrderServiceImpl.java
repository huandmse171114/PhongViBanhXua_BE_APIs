package com.phongvi.order.service;

import org.springframework.stereotype.Service;

import com.phongvi.order.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository repository;
	private final OrderMappingService mappingService;
	
	
}
