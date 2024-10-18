package com.phongvi.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginDTO(
			@NotEmpty(message = "Tên đăng nhập không được để trống.")
			String username,
			@NotEmpty(message = "Mật khẩu không được để trống.")
			String password
		) {

}
