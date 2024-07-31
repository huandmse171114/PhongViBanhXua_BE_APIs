package com.phongvi.combo_item.service;

import org.springframework.stereotype.Service;

import com.phongvi.combo_item.ComboItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboItemServiceImpl implements ComboItemService {
	private final ComboItemRepository repository;
	private final ComboItemMappingService mappingService;
	
	
}
