package com.phongvi.blog_category.service;

import org.springframework.stereotype.Service;

import com.phongvi.blog_category.BlogCategory;
import com.phongvi.blog_category.BlogCategoryRepository;
import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.blog_category.dto.BlogCategoryAdminResponseDTO;
import com.phongvi.blog_category.dto.BlogCategoryCreateDTO;
import com.phongvi.blog_category.dto.BlogCategoryResponseDTO;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogCategoryMappingService {
	private final BlogCategoryRepository repository;
	
	public BlogCategoryAdminResponseDTO blogCategoryToBlogCategoryAdminResponseDTO(BlogCategory blogCategory) {
		return BlogCategoryAdminResponseDTO.builder()
				.id(blogCategory.getId())
				.name(blogCategory.getName())
				.description(blogCategory.getDescription())
				.status(blogCategory.getStatus())
				.createdAt(blogCategory.getCreatedAt())
				.createdBy(blogCategory.getCreatedBy())
				.lastChangedAt(blogCategory.getLastChangedAt())
				.lastChangedBy(blogCategory.getLastChangedBy())
				.build();
	}
	
	public BlogCategoryResponseDTO blogCategoryToBlogCategoryResponseDTO(BlogCategory blogCategory) {
		return BlogCategoryResponseDTO.builder()
				.id(blogCategory.getId())
				.name(blogCategory.getName())
				.description(blogCategory.getDescription())
				.build();
	}
	
	public BlogCategory blogCategoryCreateDTOToBlogCategory(BlogCategoryCreateDTO blogCategoryDTO) {
		return BlogCategory.builder()
				.name(blogCategoryDTO.name())
				.description(blogCategoryDTO.description())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("")
				.status(BlogCategoryStatus.ACTIVE)
				.build();
	}
}
