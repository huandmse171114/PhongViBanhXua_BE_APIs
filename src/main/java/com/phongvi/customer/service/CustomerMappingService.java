package com.phongvi.customer.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.customer.Customer;
import com.phongvi.customer.CustomerStatus;
import com.phongvi.user.User;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerMappingService {
	
	public Customer userToCustomer(User user) {
		return Customer.builder()
				.fullname(user.getFullname())
				.email(user.getEmail())
				.phone(user.getPhone())
				.user(user)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.status(CustomerStatus.ACTIVE)
				.build();
	}

}
