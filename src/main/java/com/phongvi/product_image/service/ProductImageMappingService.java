package com.phongvi.product_image.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.product.dto.ProductImageProductCreateDTO;
import com.phongvi.product.dto.ProductImageProductResponseDTO;
import com.phongvi.product.dto.ProductImageProductUpdateDTO;
import com.phongvi.product_image.ProductImage;
import com.phongvi.product_image.ProductImageStatus;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageMappingService {
	
	public ProductImageProductResponseDTO productImageToProductImageProductResponseDTO(
				ProductImage productImage
			) {
		return ProductImageProductResponseDTO.builder()
				.id(productImage.getId())
				.index(productImage.getIndex())
				.source(productImage.getSource())
				.description(productImage.getDescription())
				.status(productImage.getStatus())
				.build();
	}
	
	public ProductImage productImageProductCreateDTOToProductImage(
			ProductImageProductCreateDTO imageDTO) {
		return ProductImage.builder()
				.index(imageDTO.index())
				.source(imageDTO.source())
				.description(imageDTO.description())
				.status(ProductImageStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.build();
	}

	public ProductImage productImageProductUpdateDTOToProductImage(ProductImageProductUpdateDTO imageDTO) {
		return ProductImage.builder()
				.index(imageDTO.index())
				.source(imageDTO.source())
				.description(imageDTO.description())
				.status(ProductImageStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName())
				.build();
	}
	
	
}
