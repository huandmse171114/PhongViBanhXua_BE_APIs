package com.phongvi.cart_item.service;

import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
	private final CartItemMappingService mappingService;
	private final CartItemRepository repository;
	
	
}
