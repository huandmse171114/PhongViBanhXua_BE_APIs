package com.phongvi.user.dto;

import com.phongvi.user.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserCreateDTO(
			@NotEmpty(message = "Vui lòng không để trống tên đăng nhập.")
			String username,
			String fullname,
			@NotEmpty(message = "Vui lòng không để trống email.")
			@Email(message = "Vui lòng nhập đúng cú pháp của email.")
			String email,
			@NotEmpty(message = "Vui lòng không để trống mật khẩu.")
			String password,
			@NotEmpty(message = "Vui lòng không để trống xác minh mật khẩu.")
			String confirmPassword,
			@Enumerated(EnumType.STRING)
			UserRole role
		) {

}
