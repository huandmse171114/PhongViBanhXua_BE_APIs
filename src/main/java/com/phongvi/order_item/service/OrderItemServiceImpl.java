package com.phongvi.order_item.service;

import org.springframework.stereotype.Service;

import com.phongvi.order_item.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
	private final OrderItemRepository repository;
	private final OrderItemMappingService mappingService;
	
	
}
