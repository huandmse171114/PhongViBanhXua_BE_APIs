package com.phongvi.supplier.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.product.dto.SupplierProductResponseDTO;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.service.ProductCategoryMappingService;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierStatus;
import com.phongvi.supplier.dto.SupplierAdminResponseDTO;
import com.phongvi.supplier.dto.SupplierCreateDTO;
import com.phongvi.supplier.dto.SupplierResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierMappingService {
	
	private final ProductCategoryMappingService productCategoryMappingService;
	private final ProductCategoryRepository productCategoryReposiotry;
	
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
	
	public Supplier supplierCreateDTOToSupplier(SupplierCreateDTO supplierDTO) {
		
		List<Long> categoriesId = supplierDTO.categories();
		
		List<ProductCategory> categories = productCategoryReposiotry.findAllByIdIn(categoriesId);
		
		if (categories.size() != categoriesId.size()) {
			throw new NoProductCategoryFoundException("Một số danh mục sản phẩm không tồn tại.");
		}
		
		return Supplier.builder()
				.name(supplierDTO.name())
				.description(supplierDTO.description())
				.ownerName(supplierDTO.ownerName())
				.phone(supplierDTO.phone())
				.openedTime(supplierDTO.openedTime())
				.closedTime(supplierDTO.closedTime())
				.street(supplierDTO.street())
				.ward(supplierDTO.ward())
				.district(supplierDTO.street())
				.province(supplierDTO.province())
				.wardCode(supplierDTO.wardCode())
				.districtId(supplierDTO.districtId())
				.provinceId(supplierDTO.provinceId())
				.shopSurroundingImg1(supplierDTO.shopSurroundingImg1())
				.shopSurroundingImg2(supplierDTO.shopSurroundingImg2())
				.shopSurroundingImg3(supplierDTO.shopSurroundingImg3())
				.categories(categories)
				.status(SupplierStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.build();
	}
	
	public SupplierProductResponseDTO supplierToSupplierProductResponseDTO(Supplier supplier) {
		return SupplierProductResponseDTO.builder()
				.id(supplier.getId())
				.name(supplier.getName())
				.ownerName(supplier.getOwnerName())
				.phone(supplier.getPhone())
				.street(supplier.getStreet())
				.ward(supplier.getWard())
				.district(supplier.getDistrict())
				.province(supplier.getProvince())
				.openedTime(supplier.getOpenedTime())
				.closedTime(supplier.getClosedTime())
				.build();
	}
	
}
