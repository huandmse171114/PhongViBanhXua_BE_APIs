package com.phongvi.cart_item.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemStatus;
import com.phongvi.cart_item.CartItemType;
import com.phongvi.cart_item.dto.CartItemResponseDTO;
import com.phongvi.combo.Combo;
import com.phongvi.combo.service.ComboMappingService;
import com.phongvi.customer.Customer;
import com.phongvi.product.Product;
import com.phongvi.product.service.ProductMappingService;
import com.phongvi.utils.Utils;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemMappingService {
	
	private final ProductMappingService productMappingService;
	private final ComboMappingService comboMappingService;
	
	public CartItemResponseDTO cartItemToCartItemResponseDTO(CartItem item) {
		return CartItemResponseDTO.builder()
				.id(item.getId())
				.quantity(item.getQuantity())
				.type(item.getType())
				.product(item.getType() == CartItemType.PRODUCT 
					? productMappingService
							.productToCartItemProductResponse(item.getProduct()) 
					: null)
				.combo(item.getType() == CartItemType.COMBO
					? comboMappingService
							.comboToCartItemComboResponse(item.getCombo())
					: null)
				.build();
	}

	public CartItem cartItemDTOToCartItem(Customer customer, Integer quantity, 
						Product product, Combo combo, CartItemType type) {

		return CartItem.builder()
				.quantity(quantity)
				.type(type)
				.status(CartItemStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.customer(customer)
				.product(product)
				.combo(combo)
				.build();
	}

}
