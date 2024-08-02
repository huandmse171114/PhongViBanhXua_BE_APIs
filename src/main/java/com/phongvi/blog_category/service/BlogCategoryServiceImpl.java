package com.phongvi.blog_category.service;

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

import com.phongvi.blog_category.BlogCategory;
import com.phongvi.blog_category.BlogCategoryRepository;
import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.blog_category.dto.BlogCategoryAdminResponseDTO;
import com.phongvi.blog_category.dto.BlogCategoryCreateDTO;
import com.phongvi.blog_category.dto.BlogCategoryUpdateDTO;
import com.phongvi.exception.NoBlogCategoryFoundException;
import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryAdminResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogCategoryServiceImpl implements BlogCategoryService {
	private final BlogCategoryRepository repository;
	private final BlogCategoryMappingService mappingService;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150; //large number of records ~ get all records
	
	@Override
	public ResponseEntity<?> getAllBlogCategoryByStatus(BlogCategoryStatus status, Integer page, Integer size,
			String name, boolean isAdminCalled) {
//		isAdminCalled is needed because admin can get list by status
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<BlogCategory> pageBlogcategories;
		
		if (status == null) {
//			Get all blog categories with ANY status
			pageBlogcategories = repository.findAllByNameContainingIgnoreCase(name, paging);
		}else {
//			Get all product with SPECIFIC status
			pageBlogcategories = repository.findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(name, status, paging);
		}
		
		List<BlogCategory> blogCategories = pageBlogcategories.getContent();
		
//		If there is no record found
		if (blogCategories.isEmpty()) {
			throw new NoBlogCategoryFoundException("Không tìm thấy danh mục blog tương ứng.");
		}
		
		List response;
		
		if (isAdminCalled) {
//			Mapping to admin response DTO
			response = blogCategories.stream()
					.map(category -> mappingService.blogCategoryToBlogCategoryAdminResponseDTO(category))
					.sorted(new Comparator<BlogCategoryAdminResponseDTO>() {
						@Override
						public int compare(BlogCategoryAdminResponseDTO o1, BlogCategoryAdminResponseDTO o2) {
							return o2.lastChangedAt().compareTo(o1.lastChangedAt());
						}
					})
					.toList();
		}else {
//			Mapping to store response DTO
			response = blogCategories.stream()
					.map(category -> mappingService.blogCategoryToBlogCategoryResponseDTO(category))
					.toList();
		}
		
		return Utils.generatePagingListResponseEntity(
				pageBlogcategories.getTotalElements(), 
				response, 
				pageBlogcategories.getTotalPages(), 
				pageBlogcategories.getNumber(), 
				HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> updateBlogCategory(Long id, BlogCategoryUpdateDTO blogCategoryDTO) {
		
		Optional<BlogCategory> blogCategoryOptional = repository.findById(id);
		
		if (blogCategoryOptional.isEmpty()) {
			throw new NoBlogCategoryFoundException("Cập nhật thất bại: không tìm thấy danh mục blog tương ứng.");
		}
		
		BlogCategory blogCategory = blogCategoryOptional.get();
		
		blogCategory.setName(blogCategoryDTO.name());
		blogCategory.setDescription(blogCategoryDTO.description());
		blogCategory.setLastChangedAt(Utils.getCurrentTimestamp());
		blogCategory.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
//		Save to db
		repository.save(blogCategory);
		
		return Utils.generateMessageResponseEntity("Cập nhật thông tin danh mục blog thành công", HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> saveBlogCategory(BlogCategoryCreateDTO blogCategoryDTO) {
		BlogCategory newBlogCategory = mappingService.blogCategoryCreateDTOToBlogCategory(blogCategoryDTO);
		
		repository.save(newBlogCategory);
		
		return Utils.generateMessageResponseEntity("Tạo mới danh mục blog thành công!", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateBlogCategoryStatus(BlogCategoryStatus status, Long id) {
		Optional<BlogCategory> blogCategoryOptional = repository.findById(id);
		
		if (blogCategoryOptional.isEmpty()) {
			if (status == BlogCategoryStatus.ACTIVE) {
				throw new NoBlogCategoryFoundException("Hiển thị danh mục blog thất bại: không tìm thấy danh mục tương ứng.");				
			}else if (status == BlogCategoryStatus.INACTIVE) {
				throw new NoBlogCategoryFoundException("Ẩn danh mục blog thất bại: không tìm thấy danh mục tương ứng.");
			}else {
				throw new NoBlogCategoryFoundException("Cập nhật trạng thái danh mục blog thất bại: không tìm thấy danh mục tương ứng.");
			}
		}
		
		BlogCategory blogCategory = blogCategoryOptional.get();
		
		blogCategory.setStatus(status);
		blogCategory.setLastChangedAt(Utils.getCurrentTimestamp());
		blogCategory.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		
		repository.save(blogCategory);
		
		if (status == BlogCategoryStatus.ACTIVE) {
			return Utils.generateMessageResponseEntity("Hiển thị danh mục blog thành công!", HttpStatus.OK);
		}else if (status == BlogCategoryStatus.INACTIVE) {
			return Utils.generateMessageResponseEntity("Ẩn danh mục blog thành công!", HttpStatus.OK);
		}else {
			return Utils.generateMessageResponseEntity("Cập nhật trạng thái danh mục blog thành công!", HttpStatus.OK);
		}
	}
	
	
}
