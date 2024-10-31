package com.phongvi.order_item.service;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemType;
import com.phongvi.order_item.OrderItem;
import com.phongvi.order_item.OrderItemStatus;
import com.phongvi.order_item.OrderItemType;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemMappingService {
	
	public OrderItem cartItemToOrderItem(CartItem item) {
		return OrderItem.builder()
				.quantity(item.getQuantity())
				.itemPrice(getPrice(item))
				.type(item.getType() == CartItemType.COMBO ? OrderItemType.COMBO
						: OrderItemType.PRODUCT)
				.combo(item.getCombo())
				.product(item.getProduct())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.status(OrderItemStatus.ACTIVE)
				.build();
	}
	
	private Long getPrice(CartItem item) {
		if (item.getType() == CartItemType.PRODUCT) {
			if (item.getProduct().getDiscountExpiry().after(java.sql.Date.valueOf(LocalDate.now()))) {
				// Has discount
				return item.getProduct().getDiscountPrice();
			} else {
				// No discount
				return item.getProduct().getPrice();
			}
		}else {
			if (item.getCombo().getDiscountExpiry().after(java.sql.Date.valueOf(LocalDate.now()))) {
				// Has discount
				return item.getCombo().getDiscountPrice();
			} else {
				// No discount
				return item.getCombo().getPrice();
			}
		}
		
	}

}
