package com.phongvi.shipper.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.shipper.Shipper;
import com.phongvi.shipper.ShipperStatus;
import com.phongvi.user.User;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipperMappingService {
	
	public Shipper userToShipper(User user) {
		return Shipper.builder()
				.fullname(user.getFullname())
				.phone(user.getPhone())
				.email(user.getEmail())
				.user(user)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.status(ShipperStatus.ACTIVE)
				.build();
	}

}
