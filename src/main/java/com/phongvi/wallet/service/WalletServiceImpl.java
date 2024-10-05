package com.phongvi.wallet.service;

import org.springframework.stereotype.Service;

import com.phongvi.wallet.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
	private final WalletRepository repository;
	private final WalletMappingService mappingService;
	
	
}
