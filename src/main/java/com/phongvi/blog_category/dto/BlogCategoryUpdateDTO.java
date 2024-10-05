package com.phongvi.blog_category.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record BlogCategoryUpdateDTO(
			@NotEmpty(message = "Tên danh mục blog không được để trống.")
			String name,
			String description
		) {

}
