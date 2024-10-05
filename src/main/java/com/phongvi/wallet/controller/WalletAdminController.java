package com.phongvi.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.wallet.service.WalletService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Wallet Management APIs")
@RequestMapping("/admin/api/v1/wallets")
public class WalletAdminController {
	private final WalletService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllWallet() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
