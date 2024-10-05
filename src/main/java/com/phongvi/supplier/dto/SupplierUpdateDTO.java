package com.phongvi.supplier.dto;

import java.sql.Time;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SupplierUpdateDTO(
			@NotEmpty(message = "Tên nhà cung cấp không được để trống.")
			String name,
			@NotEmpty(message = "Mô tả không được để trống.")
			String description,
			@NotEmpty(message = "Tên của chủ sở hữu không được để trống.")
			String ownerName,
			@NotEmpty(message = "Số điện thoại liên hệ không được để trống.")
			String phone,
			@NotNull(message = "Giờ mở cửa không được để trống.")
			Time openedTime,
			@NotNull(message = "Giờ đóng cửa không được để trống.")
			Time closedTime,
			String street,
			@NotEmpty(message = "Thông tin phường/xã không được để trống.")
			String ward,
			@NotEmpty(message = "Thông tin quận/huyện không được để trống.")
			String district,
			@NotEmpty(message = "Thông tin tỉnh/thành phố không được để trống.")
			String province,
			@NotEmpty(message = "Mã phường/xã không được để trống.")
			String wardCode,
			@NotNull(message = "Mã quận/huyện không được để trống.")
			Integer districtId,
			@NotNull(message = "Mã tỉnh/thành phố không được để trống.")
			Integer provinceId,
			@NotEmpty(message = "Cần ít nhất một ảnh mô tả.")
			String shopSurroundingImg1,
			String shopSurroundingImg2,
			String shopSurroundingImg3,
			@NotEmpty(message = "Cần ít nhất một danh mục sản phẩm được liên kết.")
			List<Long> categories
		) {

}
