package com.phongvi.combo_item.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.phongvi.cart_item.dto.CartItemComboItemResponse;
import com.phongvi.combo_item.ComboItem;
import com.phongvi.combo_item.dto.ComboItemAdminResponseDTO;
import com.phongvi.combo_item.dto.ComboItemResponseDTO;
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
	
	public ComboItemAdminResponseDTO comboItemToComboItemAdminResponseDTO(ComboItem item) {
		return ComboItemAdminResponseDTO.builder()
				.quantity(item.getQuantity())
				.product(productMappingService
						.productToProductComboItemAdminResponseDTO(item.getProduct()))
				.createdAt(item.getCreatedAt())
				.createdBy(item.getCreatedBy())
				.lastChangedAt(item.getLastChangedAt())
				.lastChangedBy(item.getLastChangedBy())
				.status(item.getStatus())
				.build();
	}
	
	public ComboItemResponseDTO comboItemToComboItemResponseDTO(ComboItem item) {
		return ComboItemResponseDTO.builder()
				.quantity(item.getQuantity())
				.product(productMappingService
						.productToProductComboItemAdminResponseDTO(item.getProduct()))
				.build();
	}
	
}
