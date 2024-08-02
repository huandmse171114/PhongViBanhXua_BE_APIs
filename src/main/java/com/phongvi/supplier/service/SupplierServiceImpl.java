package com.phongvi.supplier.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.exception.NoSupplierFoundException;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierRepository;
import com.phongvi.supplier.SupplierStatus;
import com.phongvi.supplier.dto.SupplierAdminResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
	private final SupplierMappingService mappingService;
	private final SupplierRepository repository;
	private final ProductCategoryRepository productCategoryRepository;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150; //large number of records ~ get all records
	
	@Override
	public ResponseEntity<?> getAllSupplierByStatus(SupplierStatus status, Integer page, Integer size, String street, String ward,
			String district, String province, String name, List<Long> categories, boolean isAdminCalled) {
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<Supplier> pageSuppliers;
		
		List<ProductCategory> categoriesDB = new ArrayList<>();
		
//		Get all suppliers with filter for categories
		if (!categories.isEmpty()) {
			categoriesDB = productCategoryRepository.findAllByIdIn(categories);
			
//			If there is some record not found
			if (categories.size() != categoriesDB.size()) {
				throw new NoProductCategoryFoundException("Một số danh mục sản phẩm không tồn tại.");
			}

			if (status == null) {
//			Get all suppliers with ANY status
				pageSuppliers = repository.findAllByNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCaseAndCategoriesIn(
						name, street, ward, district, province, categoriesDB, paging);
			}else {
//			Get all suppliers with SPECIFIC status
				pageSuppliers = repository.findAllByStatusAndNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCaseAndCategoriesIn(
						status, name, street, ward, district, province, categoriesDB, paging);
			}
		
//		Get all suppliers without filter for categories
		}else {
			if (status == null) {
//				Get all suppliers with ANY status
					pageSuppliers = repository.findAllByNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCase(
							name, street, ward, district, province, paging);
				}else {
//				Get all suppliers with SPECIFIC status
					pageSuppliers = repository.findAllByStatusAndNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCase(
							status, name, street, ward, district, province, paging);
				}
		}
		
		
		List<Supplier> suppliers = pageSuppliers.getContent();
		
//		If there is no record found
		if (suppliers.isEmpty()) {
			throw new NoSupplierFoundException("Không tìm thấy nhà cung cấp tương ứng.");
		}
		
		List response;
		
		if (isAdminCalled) {
//			Mapping to admin response DTO
			response = suppliers.stream()
					.map(supplier -> mappingService.supplierToSupplierAdminResponseDTO(supplier))
					.sorted(new Comparator<SupplierAdminResponseDTO>() {
						@Override
						public int compare(SupplierAdminResponseDTO o1, SupplierAdminResponseDTO o2) {
							return o2.createdAt().compareTo(o1.createdAt());
						}
					})
					.toList();
		}else {
//			Mapping to store response DTO
			response = suppliers.stream()
					.map(supplier -> mappingService.supplierToSupplierResponseDTO(supplier))
					.toList();
		}
		
		return Utils.generatePagingListResponseEntity(
				pageSuppliers.getTotalElements(), 
				response, 
				pageSuppliers.getTotalPages(), 
				pageSuppliers.getNumber(), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateSupplierStatus(SupplierStatus status, Long id) {
		
		Optional<Supplier> supplierOptional = repository.findById(id);
		
		if (supplierOptional.isEmpty()) {
			if (status == SupplierStatus.ACTIVE) {
				throw new NoSupplierFoundException("Hiển thị nhà cung cấp thất bại: không tìm thấy nhà cung cấp tương ứng.");				
			}else if (status == SupplierStatus.INACTIVE) {
				throw new NoSupplierFoundException("Ẩn nhà cung cấp thất bại: không tìm thấy nhà cung cấp tương ứng.");
			}else {
				throw new NoSupplierFoundException("Cập nhật trạng thái nhà cung cấp thất bại: không tìm thấy nhà cung cấp tương ứng.");
			}
		}
		
		Supplier supplier = supplierOptional.get();
		
		supplier.setStatus(status);
		supplier.setLastChangedAt(Utils.getCurrentTimestamp());
		supplier.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
		repository.save(supplier);
		
		if (status == SupplierStatus.ACTIVE) {
			return Utils.generateMessageResponseEntity("Hiển thị nhà cung cấp thành công!", HttpStatus.OK);
		}else if (status == SupplierStatus.INACTIVE) {
			return Utils.generateMessageResponseEntity("Ẩn nhà cung cấp thành công!", HttpStatus.OK);
		}else {
			return Utils.generateMessageResponseEntity("Cập nhật trạng thái nhà cung cấp thành công!", HttpStatus.OK);
		}
	}
	
	
}
