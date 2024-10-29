package com.phongvi.order_item.service;

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
				.itemPrice(item.getProduct().getPrice())
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

}
