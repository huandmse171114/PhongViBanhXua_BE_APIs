package com.phongvi.product_category.service;

import java.util.Comparator;
import java.util.HashMap;
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
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryAdminResponseDTO;
import com.phongvi.product_category.dto.ProductCategoryCreateDTO;
import com.phongvi.product_category.dto.ProductCategoryUpdateDTO;
import com.phongvi.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
	private final ProductCategoryRepository repository;
	private final ProductCategoryMappingService mappingService;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150; //large number of records ~ get all records

	@Override
	public ResponseEntity<?> getAllProductCategoryByStatus(ProductCategoryStatus status, Integer page, Integer size,
			String name, boolean isAdminCalled) {
//		isAdminCalled is needed because admin can get list by status
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<ProductCategory> pageProductCategories;
		
		if (status == null) {
//			Get all product categories with ANY status
			pageProductCategories = repository.findAllByNameContainingIgnoreCase(name, paging);			
		}else {
//			Get all product categories with SPECIFIC status
			pageProductCategories = repository.findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(name, status, paging);			
		}
		
		List<ProductCategory> productCategories = pageProductCategories.getContent();
		
//		If there is no record found
		if (productCategories.isEmpty()) {
			throw new NoProductCategoryFoundException("Không tìm thấy danh mục sản phẩm tương ứng.");
		}
		
		List response;
		
		if (isAdminCalled) {
//			Mapping to admin response DTO
			response = productCategories.stream()
					.map(category -> mappingService.productCategoryToProductCategoryAdminResponseDTO(category))
					.sorted(new Comparator<ProductCategoryAdminResponseDTO>() {
						@Override
						public int compare(ProductCategoryAdminResponseDTO o1, ProductCategoryAdminResponseDTO o2) {
							return o2.createdAt().compareTo(o1.createdAt());
						}
					})
					.toList();			
		}else {
//			Mapping to store response DTO
			response = productCategories.stream()
					.map(category -> mappingService.productCategoryToProductCategoryResponseDTO(category))
					.toList();
		}
		
		return Utils.generatePagingListResponseEntity(
				pageProductCategories.getTotalElements(), 
				response, 
				pageProductCategories.getTotalPages(), 
				pageProductCategories.getNumber(), 
				HttpStatus.OK);
		
		
	}

	@Override
	public ResponseEntity<?> getProductCategoryByIdAndStatus(ProductCategoryStatus status, Long id,
			boolean isAdminCalled) {
		
		Optional<ProductCategory> productCategoryOptional;
		
		if (status == null) {
//			Find Product Category By ID only
			productCategoryOptional = repository.findById(id);			
		}else {
//			Find Product Category By ID and Status
			productCategoryOptional = repository.findByIdAndStatus(id, status);
		}

//		Check if there is product category found
		if (productCategoryOptional.isEmpty()) {
			throw new NoProductCategoryFoundException("Không tìm thấy danh mục sản phẩm với id [ " + id + " ] tương ứng.");
		}
		
		ProductCategory productCategory = productCategoryOptional.get();
		
		var response = new HashMap<String, Object>();
		
		if (isAdminCalled) {
//			Mapping product category to admin response DTO
			response.put("data", mappingService.productCategoryToProductCategoryAdminResponseDTO(productCategory));			
		}else {
//			Mapping product category to store response DTO
			response.put("data", mappingService.productCategoryToProductCategoryResponseDTO(productCategory));
		}
		
		response.put("timestamp", Utils.getCurrentTimestamp());
		response.put("status", HttpStatus.OK.value());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateProductCategory(Long id, ProductCategoryUpdateDTO productCategoryDto) {
		
		Optional<ProductCategory> productCategoryOptional = repository.findById(id);
		
//		Check if the needed product category is existed or not
		if (productCategoryOptional.isEmpty()) {
			throw new NoProductCategoryFoundException("Cập nhật thất bại: không tìm thấy danh mục sản phẩm tương ứng.");
		}
		
		ProductCategory productCategory = productCategoryOptional.get();
		
//		Check if there is parent category to update
		if (productCategoryDto.parentId() != null) {
			Optional<ProductCategory> parentProductCategoryOptional = repository.findById(productCategoryDto.parentId());
			
			if (parentProductCategoryOptional.isEmpty()) {
				throw new NoProductCategoryFoundException("Cập nhật thất bại: không tìm thấy danh mục sản phẩm cha tương ứng.");
			}
			
			ProductCategory parentProductCategory = parentProductCategoryOptional.get();
			
			productCategory.setParentCategory(parentProductCategory);
			
//		If not, set parent category to null
		}else {
			productCategory.setParentCategory(null);
		}
		
		productCategory.setName(productCategoryDto.name());
		productCategory.setDescription(productCategoryDto.description());
		productCategory.setCategoryImg(productCategory.getCategoryImg());
		productCategory.setLastChangedAt(Utils.getCurrentTimestamp());
		productCategory.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
//		Save to db
		repository.save(productCategory);
		
		return Utils.generateMessageResponseEntity("Cập nhật thông tin danh mục sản phẩm thành công!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> saveProductCategory(ProductCategoryCreateDTO productCategoryDto) {
		ProductCategory newProductCategory = mappingService.productCategoryCreateDTOToProductCategory(productCategoryDto);
		
		repository.save(newProductCategory);
		
		return Utils.generateMessageResponseEntity("Tạo mới danh mục sản phẩm thành công!", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateProductCategoryStatus(ProductCategoryStatus status, Long id) {
		
		Optional<ProductCategory> productCategoryOptional = repository.findById(id);
		
		if (productCategoryOptional.isEmpty()) {
			if (status == ProductCategoryStatus.ACTIVE) {
				throw new NoProductCategoryFoundException("Hiển thị danh mục sản phẩm thất bại: không tìm thấy danh mục tương ứng.");				
			}else if (status == ProductCategoryStatus.INACTIVE) {
				throw new NoProductCategoryFoundException("Ẩn danh mục sản phẩm thất bại: không tìm thấy danh mục tương ứng.");
			}else {
				throw new NoProductCategoryFoundException("Cập nhật trạng thái danh mục sản phẩm thất bại: không tìm thấy danh mục tương ứng.");
			}
		}
		
		ProductCategory productCategory = productCategoryOptional.get();
		
		productCategory.setStatus(status);
		productCategory.setLastChangedAt(Utils.getCurrentTimestamp());
		productCategory.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
		repository.save(productCategory);
		
		if (status == ProductCategoryStatus.ACTIVE) {
			return Utils.generateMessageResponseEntity("Hiển thị danh mục sản phẩm thành công!", HttpStatus.OK);
		}else if (status == ProductCategoryStatus.INACTIVE) {
			return Utils.generateMessageResponseEntity("Ẩn danh mục sản phẩm thành công!", HttpStatus.OK);
		}else {
			return Utils.generateMessageResponseEntity("Cập nhật trạng thái danh mục sản phẩm thành công!", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> getAllProductCategoryChildrenByIdAndStatus(ProductCategoryStatus status, Long id,
			Integer page, Integer size) {
		Optional<ProductCategory> parentProductCategoryOptional = repository.findById(id);
		
		if (parentProductCategoryOptional.isEmpty()) {
			throw new NoProductCategoryFoundException("Không tìm thấy danh mục tương ứng.");
		}
		
		ProductCategory parentProductCategory = parentProductCategoryOptional.get();
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<ProductCategory> pageChildrenProductCategories = repository.findAllByParentCategoryOrderByNameAsc(parentProductCategory, paging);
		
		List<ProductCategory> childrenProductCategories = pageChildrenProductCategories.getContent();
		
		if (childrenProductCategories.isEmpty()) {
			throw new NoProductCategoryFoundException("Danh mục không có danh mục con.");
		}
		
		var response = childrenProductCategories.stream()
				.map(category -> mappingService.productCategoryToProductCategoryResponseDTO(category))
				.toList();
		
		return Utils.generatePagingListResponseEntity(
				pageChildrenProductCategories.getTotalElements(), 
				response, 
				pageChildrenProductCategories.getTotalPages(), 
				pageChildrenProductCategories.getNumber(), 
				HttpStatus.OK);
	}
	
	
}
