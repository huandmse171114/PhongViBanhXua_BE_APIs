package com.phongvi.product_category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;




@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	
	Optional<ProductCategory> findByIdAndStatus(Long id, ProductCategoryStatus status);
	
	Page<ProductCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<ProductCategory> findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(String name, ProductCategoryStatus status, Pageable pageable);
	
	Page<ProductCategory> findAllByParentCategoryOrderByNameAsc(ProductCategory parentCategory, Pageable pageable);
	
	List<ProductCategory> findAllByIdIn(Collection<Long> ids);
}
