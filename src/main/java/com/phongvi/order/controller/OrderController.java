package com.phongvi.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.order.dto.OrderCreateDTO;
import com.phongvi.order.service.OrderService;
import com.phongvi.product.GhnApis;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order Management APIs")
@RequestMapping("/store/api/v1/orders")
public class OrderController {
	private final OrderService service;
	private final GhnApis ghnApis;
	
	 @GetMapping("/districts")
	    public String getDistrictList() {
	        return ghnApis.getDistrictList();
	    }
	 @GetMapping("/wards")
	    public String getWardList(@RequestParam String districtId) {
	        return ghnApis.getWardList(districtId);
	    }
	
	@GetMapping("")
	public ResponseEntity<?> getAllActiveOrder() {
		return new ResponseEntity<>("Not supported yet!", HttpStatus.OK);
	}
	
	@PostMapping("/{username}")
	public ResponseEntity<?> createOrder(
				@PathVariable("username") String username,
				@Valid @RequestBody OrderCreateDTO orderDTO
			) {
		return service.createOrder(username, orderDTO);
	}
	
}
