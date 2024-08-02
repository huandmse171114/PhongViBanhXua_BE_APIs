package com.phongvi.supplier.service;

import org.springframework.stereotype.Service;

import com.phongvi.product_category.service.ProductCategoryMappingService;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.dto.SupplierAdminResponseDTO;
import com.phongvi.supplier.dto.SupplierResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierMappingService {
	
	private final ProductCategoryMappingService productCategoryMappingService;
	
	public SupplierAdminResponseDTO supplierToSupplierAdminResponseDTO(Supplier supplier) {
		return SupplierAdminResponseDTO.builder()
				.id(supplier.getId())
				.name(supplier.getName())
				.description(supplier.getDescription())
				.ownerName(supplier.getOwnerName())
				.phone(supplier.getPhone())
				.openedTime(supplier.getOpenedTime())
				.closedTime(supplier.getClosedTime())
				.street(supplier.getStreet())
				.ward(supplier.getWard())
				.district(supplier.getDistrict())
				.province(supplier.getProvince())
				.districtId(supplier.getDistrictId())
				.provinceId(supplier.getProvinceId())
				.wardCode(supplier.getWardCode())
				.shopSurroundingImg1(supplier.getShopSurroundingImg1())
				.shopSurroundingImg2(supplier.getShopSurroundingImg2())
				.shopSurroundingImg3(supplier.getShopSurroundingImg3())
				.totalRating(supplier.getTotalRating())
				.averageStar(supplier.getAverageStar())
				.status(supplier.getStatus())
				.createdAt(supplier.getCreatedAt())
				.createdBy(supplier.getCreatedBy())
				.lastChangedAt(supplier.getLastChangedAt())
				.lastChangedBy(supplier.getLastChangedBy())
				.categories(supplier.getCategories().stream()
						.map(category -> productCategoryMappingService.productCategoryToProductCategoryResponseDTO(category))
						.toList())
				.build();
	}
	
	public SupplierResponseDTO supplierToSupplierResponseDTO(Supplier supplier) {
		return SupplierResponseDTO.builder()
				.id(supplier.getId())
				.name(supplier.getName())
				.description(supplier.getDescription())
				.ownerName(supplier.getOwnerName())
				.phone(supplier.getPhone())
				.openedTime(supplier.getOpenedTime())
				.closedTime(supplier.getClosedTime())
				.street(supplier.getStreet())
				.ward(supplier.getWard())
				.district(supplier.getDistrict())
				.province(supplier.getProvince())
				.districtId(supplier.getDistrictId())
				.provinceId(supplier.getProvinceId())
				.wardCode(supplier.getWardCode())
				.shopSurroundingImg1(supplier.getShopSurroundingImg1())
				.shopSurroundingImg2(supplier.getShopSurroundingImg2())
				.shopSurroundingImg3(supplier.getShopSurroundingImg3())
				.totalRating(supplier.getTotalRating())
				.averageStar(supplier.getAverageStar())
				.categories(supplier.getCategories().stream()
						.map(category -> productCategoryMappingService.productCategoryToProductCategoryRelateResponse(category))
						.toList())
				.build();
	}
}
