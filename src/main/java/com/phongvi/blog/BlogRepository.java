package com.phongvi.blog;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongvi.blog_category.BlogCategory;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
//	Page<Blog> findAllByTitleContainingIgnoreCaseAndCreatedByAndCategoryAndCreatedAtBetweenOrderByCreatedAtDesc(
//			String title, String author, BlogCategory category, LocalDate startDate, LocalDate endDate, Pageable pagable);
//	
//	Page<Blog> findAllByTitleContainingIgnoreCaseAndCreatedByAndCategoryAndStatusCreatedAtBetweenOrderByCreatedAtDesc(
//			String title, String author, BlogCategory category, BlogStatus status, LocalDate startDate, LocalDate endDate, Pageable pagable);
}
