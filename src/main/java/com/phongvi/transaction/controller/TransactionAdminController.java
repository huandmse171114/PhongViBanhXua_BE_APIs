package com.phongvi.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.transaction.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "Transaction Management APIs")
@RequestMapping("/admin/api/v1/transactions")
public class TransactionAdminController {
	private final TransactionService service;
	
	@GetMapping("")
	public ResponseEntity<?> getAllTransaction() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
}
