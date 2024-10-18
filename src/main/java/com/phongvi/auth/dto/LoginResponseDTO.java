package com.phongvi.auth.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record LoginResponseDTO(
			String username,
			String jwtToken,
			List<String> roles
		) {

}
