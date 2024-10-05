package com.phongvi.combo.service;

import org.springframework.stereotype.Service;

import com.phongvi.combo.ComboRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
	private final ComboRepository repository;
	private final ComboMappingService mappingService;
	
	
}
