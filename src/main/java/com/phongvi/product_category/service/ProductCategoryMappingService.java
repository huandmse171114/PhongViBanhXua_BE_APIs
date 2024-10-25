package com.phongvi.product_category.service;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.product.dto.CategoryProductResponseDTO;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryAdminResponseDTO;
import com.phongvi.product_category.dto.ProductCategoryCreateDTO;
import com.phongvi.product_category.dto.ProductCategoryRelateResponse;
import com.phongvi.product_category.dto.ProductCategoryResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryMappingService {
	private final ProductCategoryRepository repository;
	
	public ProductCategoryAdminResponseDTO productCategoryToProductCategoryAdminResponseDTO(ProductCategory productCategory) {
		return ProductCategoryAdminResponseDTO.builder()
				.id(productCategory.getId())
				.parentId(productCategory.getParentCategory() != null 
					? productCategory.getParentCategory().getId()
					: null)
				.parentName(productCategory.getParentCategory() != null
					? productCategory.getParentCategory().getName()
					: null)
				.name(productCategory.getName())
				.description(productCategory.getDescription())
				.categoryImg(productCategory.getCategoryImg())
				.status(productCategory.getStatus())
				.createdAt(productCategory.getCreatedAt())
				.createdBy(productCategory.getCreatedBy())
				.lastChangedAt(productCategory.getLastChangedAt())
				.lastChangedBy(productCategory.getLastChangedBy())
				.build();
	}
	
	public ProductCategoryResponseDTO productCategoryToProductCategoryResponseDTO(ProductCategory productCategory) {
		return ProductCategoryResponseDTO.builder()
				.id(productCategory.getId())
				.parentId(productCategory.getParentCategory() != null 
					? productCategory.getParentCategory().getId()
					: null)
				.parentName(productCategory.getParentCategory() != null
					? productCategory.getParentCategory().getName()
					: null)
				.name(productCategory.getName())
				.description(productCategory.getDescription())
				.categoryImg(productCategory.getCategoryImg())
				.build();
	}
	
	public ProductCategoryRelateResponse productCategoryToProductCategoryRelateResponse(ProductCategory productCategory) {
		return ProductCategoryRelateResponse.builder()
				.id(productCategory.getId())
				.name(productCategory.getName())
				.description(productCategory.getDescription())
				.build();
	}
	
	public ProductCategory productCategoryCreateDTOToProductCategory(ProductCategoryCreateDTO productCategoryDTO) {
		
		ProductCategory parentProductCategory = null;
		
//		Check if there is parent product category
		if (productCategoryDTO.parentId() != null) {
			Optional<ProductCategory> parentProductCategoryOptional = repository.findById(productCategoryDTO.parentId());
			
//			Check if parent product category is existed
			if (parentProductCategoryOptional.isEmpty()) {
				throw new NoProductCategoryFoundException("Tạo mới thất bại: không tìm thấy danh mục sản phẩm cha tương ứng.");
			}
			
			parentProductCategory = parentProductCategoryOptional.get();
		}
		
		return ProductCategory.builder()
				.name(productCategoryDTO.name())
				.description(productCategoryDTO.description())
				.categoryImg(productCategoryDTO.categoryImg())
				.parentCategory(parentProductCategory)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
				.status(ProductCategoryStatus.ACTIVE)
				.build();
	}
	
	public CategoryProductResponseDTO productCategoryToCategoryProductResponseDTO(ProductCategory category) {
		return CategoryProductResponseDTO.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.categoryImg(category.getCategoryImg())
				.status(category.getStatus())
				.build();
	}
	
	
}
