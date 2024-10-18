package com.phongvi.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record GoogleTokenDTO(
			@NotEmpty(message = "Thông tin về credentials không được để trống.")
			String credential,
			@NotEmpty(message = "Thông tin về client id không được để trống.")
			String clientId,
			String select_by
		) {

}
