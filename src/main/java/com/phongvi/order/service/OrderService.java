package com.phongvi.order.service;

import org.springframework.http.ResponseEntity;

import com.phongvi.order.dto.OrderCreateDTO;

import jakarta.validation.Valid;

public interface OrderService {

	ResponseEntity<?> createOrder(String username, @Valid OrderCreateDTO orderDTO);

}
