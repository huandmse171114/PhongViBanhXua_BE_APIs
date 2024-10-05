package com.phongvi.blog_category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {
	
	Page<BlogCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<BlogCategory> findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(String name, BlogCategoryStatus status, Pageable pageable);
}
