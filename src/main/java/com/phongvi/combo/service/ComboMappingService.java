package com.phongvi.combo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phongvi.cart_item.dto.CartItemComboResponse;
import com.phongvi.combo.Combo;
import com.phongvi.combo.ComboStatus;
import com.phongvi.combo.dto.ComboAdminResponseDTO;
import com.phongvi.combo.dto.ComboCreateDTO;
import com.phongvi.combo.dto.ComboResponseDTO;
import com.phongvi.combo_item.service.ComboItemMappingService;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboMappingService {
	
	private final ComboItemMappingService itemMappingService;
	
	public CartItemComboResponse comboToCartItemComboResponse(Combo combo) {
		return CartItemComboResponse.builder()
				.id(combo.getId())
				.name(combo.getName())
				.price(combo.getPrice())
				.discountExpiry(combo.getDiscountExpiry())
				.discountPrice(combo.getDiscountPrice())
				.items(combo.getItems() != null && !combo.getItems().isEmpty()
						? combo.getItems().stream()
								.map(item -> itemMappingService.comboItemToCartItemComboItemResponse(item))
								.toList()
						: null)
				.build();
	}
	
	public ComboAdminResponseDTO comboToComboAdminResponseDTO(Combo combo) {
		return ComboAdminResponseDTO.builder()
				.id(combo.getId())
				.name(combo.getName())
				.description(combo.getDescription())
				.price(combo.getPrice())
				.discountExpiry(combo.getDiscountExpiry())
				.discountPrice(combo.getDiscountPrice())
				.totalRating(combo.getTotalRating())
				.averageStar(combo.getAverageStar())
				.dailyStock(combo.getDailyStock())
				.status(combo.getStatus())
				.createdAt(combo.getCreatedAt())
				.createdBy(combo.getCreatedBy())
				.lastChangedAt(combo.getLastChangedAt())
				.lastChangedBy(combo.getLastChangedBy())
				.items(combo.getItems().stream()
						.map(item -> itemMappingService.comboItemToComboItemAdminResponseDTO(item))
						.toList())
				.build();
	}
	
	public ComboResponseDTO comboToComboResponseDTO(Combo combo) {
		
		return ComboResponseDTO.builder()
				.id(combo.getId())
				.name(combo.getName())
				.description(combo.getDescription())
				.price(combo.getPrice())
				.discountExpiry(combo.getDiscountExpiry())
				.discountPrice(combo.getDiscountPrice())
				.totalRating(combo.getTotalRating())
				.averageStar(combo.getAverageStar())
				.dailyStock(combo.getDailyStock())
				.items(combo.getItems().stream()
						.map(item -> itemMappingService.comboItemToComboItemResponseDTO(item))
						.toList())
				.build();
	}

	public Combo comboCreateDTOToCombo(ComboCreateDTO comboDTO) {
		
		return Combo.builder()
				.name(comboDTO.name())
				.description(comboDTO.description())
				.price(comboDTO.price())
				.discountPrice(comboDTO.discountPrice())
				.discountExpiry(comboDTO.discountExpiry())
				.dailyStock(comboDTO.dailyStock())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("")
				.status(ComboStatus.ACTIVE)
				.build();
	}

}
