package com.phongvi.product_category.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ProductCategoryCreateDTO(
			@NotEmpty(message = "Tên danh mục sản phẩm không được để trống")
			String name,
			String description,
			String categoryImg,
			Long parentId
		) {

}
