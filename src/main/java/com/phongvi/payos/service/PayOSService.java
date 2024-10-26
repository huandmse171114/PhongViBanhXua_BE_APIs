package com.phongvi.payos.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
	public CheckoutResponseData createPaymentLink() {

        try {
            final String productName = "";
            final String description = "";
            final String returnUrl = "";
            final String cancelUrl = "";
            final int price = 0;
            
            // Gen order code
            String currentTimeString = String.valueOf(String.valueOf(new Date().getTime()));
            
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

            ItemData item = ItemData.builder().name(productName).price(price).quantity(1).build();

            PaymentData paymentData = PaymentData.builder().orderCode(orderCode).description(description).amount(price)
                    .item(item).returnUrl(returnUrl).cancelUrl(cancelUrl).build();

            CheckoutResponseData data = payOS.createPaymentLink(paymentData);

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	

	
}
