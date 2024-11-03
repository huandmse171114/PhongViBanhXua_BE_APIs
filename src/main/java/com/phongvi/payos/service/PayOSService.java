package com.phongvi.payos.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemType;
import com.phongvi.order.Order;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;

@Service
@RequiredArgsConstructor
public class PayOSService {
	
	private final PayOS payOS;
	
	public CheckoutResponseData createPaymentLink(Order order, List<CartItem> cartItems) {

        try {
            final String returnUrl = "";
            final String cancelUrl = "https://chatgpt.com/c/671b4451-2888-8012-9c02-c68170a33e3b";
            
            // Gen order code
            String currentTimeString = String.valueOf(String.valueOf(new Date().getTime()));
            
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

            List<ItemData> items = cartItems.stream().map(item -> ItemData.builder()
            			.name(item.getType() == CartItemType.COMBO 
            					? item.getCombo().getName() 
    							: item.getProduct().getName())
            			.price(getPrice(item))
            			.quantity(item.getQuantity())
            			.build())
            		.toList();

            PaymentData paymentData = PaymentData.builder()
            		.orderCode(orderCode)
            		.description("PVBX" + orderCode)
            		.amount(Integer.parseInt(order.getTotalAmount() + ""))
                    .items(items)
                    .buyerEmail(order.getCustomer().getEmail())
                    .buyerAddress(order.getStreet() + "," + order.getWard() + "," + order.getDistrict())
                    .buyerName(order.getReceiverName())
                    .buyerPhone(order.getContactNumber())
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .build();

            CheckoutResponseData data = payOS.createPaymentLink(paymentData);

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	private Integer getPrice(CartItem item) {
		if (item.getType() == CartItemType.PRODUCT) {
			if (item.getProduct().getDiscountExpiry().after(java.sql.Date.valueOf(LocalDate.now()))) {
				// Has discount
				return Integer.parseInt("" + item.getProduct().getDiscountPrice());
			} else {
				// No discount
				return Integer.parseInt("" + item.getProduct().getPrice());
			}
		}else {
			if (item.getCombo().getDiscountExpiry().after(java.sql.Date.valueOf(LocalDate.now()))) {
				// Has discount
				return Integer.parseInt("" + item.getCombo().getDiscountPrice());
			} else {
				// No discount
				return Integer.parseInt("" + item.getCombo().getPrice());
			}
		}
		
	}
	
}
