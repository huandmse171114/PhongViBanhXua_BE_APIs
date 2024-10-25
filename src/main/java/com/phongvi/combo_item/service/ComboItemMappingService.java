package com.phongvi.combo_item.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.phongvi.cart_item.dto.CartItemComboItemResponse;
import com.phongvi.combo_item.ComboItem;
import com.phongvi.product.service.ProductMappingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboItemMappingService {
	
	private final ProductMappingService productMappingService;
	
	public CartItemComboItemResponse comboItemToCartItemComboItemResponse(ComboItem item) {
		return CartItemComboItemResponse.builder()
				.id(item.getId())
				.quantity(item.getQuantity())
				.product(item.getProduct() != null 
					? productMappingService
							.productToCartItemProductResponse(item.getProduct()) 
					: null)
				.build();
	}
	
}
