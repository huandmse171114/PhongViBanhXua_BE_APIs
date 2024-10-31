package com.phongvi.combo.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phongvi.combo.Combo;
import com.phongvi.combo.ComboRepository;
import com.phongvi.combo.ComboStatus;
import com.phongvi.combo.dto.ComboAdminResponseDTO;
import com.phongvi.combo.dto.ComboCreateDTO;
import com.phongvi.combo.dto.ComboResponseDTO;
import com.phongvi.combo_item.ComboItem;
import com.phongvi.combo_item.ComboItemRepository;
import com.phongvi.combo_item.ComboItemStatus;
import com.phongvi.combo_item.dto.ComboItemComboCreateDTO;
import com.phongvi.exception.NoComboFoundException;
import com.phongvi.exception.NoProductFoundException;
import com.phongvi.product.Product;
import com.phongvi.product.ProductRepository;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
	private final ComboRepository repository;
	private final ComboMappingService mappingService;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150;
	private final ProductCategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final ComboItemRepository comboItemRepository;
	
	@Override
	public ResponseEntity<?> getAllComboByStatus(ComboStatus status, Integer page, Integer size, String name,
			boolean isAdminCalled) {
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<Combo> pageCombos;
		

		if (status == null) {
			pageCombos = repository.findAllByNameContainingIgnoreCase(name, paging);
		}else {
			pageCombos = repository.findAllByStatusAndNameContainingIgnoreCase(status, name, paging);
		}
		
		List<Combo> combos = pageCombos.getContent();
		
		if (combos.isEmpty()) throw new NoComboFoundException("Không tìm thấy combo tương ứng.");
		
		List response;
		
		if (isAdminCalled) {
			// Mapping to admin response DTO
			response = combos.stream()
					.map(combo -> mappingService.comboToComboAdminResponseDTO(combo))
					.sorted(new Comparator<ComboAdminResponseDTO>() {
						@Override
						public int compare(ComboAdminResponseDTO o1, ComboAdminResponseDTO o2) {
							return o2.lastChangedAt().compareTo(o1.lastChangedAt());
						}
					})
					.toList();
		} else {
			// Mapping to store response DTO
			response = combos.stream()
					.map(combo -> mappingService.comboToComboResponseDTO(combo))
					.sorted(new Comparator<ComboResponseDTO>() {
						@Override
						public int compare(ComboResponseDTO o1, ComboResponseDTO o2) {
							return o2.name().compareTo(o1.name());
						}
					})
					.toList();
		}
		
		return Utils.generatePagingListResponseEntity(
				pageCombos.getTotalElements(), 
				response, 
				pageCombos.getTotalPages(), 
				pageCombos.getNumber(), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> saveCombo(ComboCreateDTO comboDTO) {
		Combo newCombo = mappingService.comboCreateDTOToCombo(comboDTO);
		
		Combo comboDB = repository.save(newCombo);
		
		List<Long> productIds = comboDTO.items().stream()
				.map(item -> item.productId())
				.toList();
		
		List<Product> products = productRepository.findAllById(productIds);
		
		if (products.size() != productIds.size()) {
			throw new NoProductFoundException("Tạo mới thất bại: không tìm thấy sản phẩm tương ứng.");
		}
		
		products.forEach(product -> {
			List<ComboItemComboCreateDTO> comboItems = comboDTO.items().stream()
					.filter(item -> item.productId() == product.getId())
					.toList();
			
			if (comboItems.size() != 1) throw new NoProductFoundException("Tạo mới thất bại: sản phẩm bị trùng.");
			
			ComboItemComboCreateDTO comboItem = comboItems.get(0);
			
			comboItemRepository.save(ComboItem.builder()
					.quantity(comboItem.quantity())
					.product(product)
					.createdAt(Utils.getCurrentTimestamp())
					.createdBy("")
					.lastChangedAt(Utils.getCurrentTimestamp())
					.lastChangedBy("")
					.combo(comboDB)
					.status(ComboItemStatus.ACTIVE)
					.build());
		});
		
		return Utils.generateMessageResponseEntity("Tạo mới combo thành công", HttpStatus.CREATED);
	}
	
	
}
