package com.phongvi.user.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phongvi.user.Provider;
import com.phongvi.user.User;
import com.phongvi.user.UserRole;
import com.phongvi.user.UserStatus;
import com.phongvi.user.dto.UserCreateDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMappingService {
	private final PasswordEncoder passwordEncoder;
	
	public User userCreateBodyDTOToUser(UserCreateDTO userDTO) {
		return User.builder()
				.username(userDTO.username().toLowerCase())
				.password(passwordEncoder.encode(userDTO.password()))
				.email(userDTO.email())
				.fullname(userDTO.fullname())
				.role(userDTO.role() == null ?
						UserRole.CUSTOMER : userDTO.role())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.provider(Provider.IN_SYSTEM)
				.status(UserStatus.ACTIVE)
				.build();
	}

}
