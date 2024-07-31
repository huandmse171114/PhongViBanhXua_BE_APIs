package com.phongvi.transaction.service;

import org.springframework.stereotype.Service;

import com.phongvi.transaction.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository repository;
	private final TransactionMappingService mappingService;
	
	
}
