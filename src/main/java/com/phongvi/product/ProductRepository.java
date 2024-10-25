package com.phongvi.product;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongvi.product_category.ProductCategory;
import com.phongvi.supplier.Supplier;
import java.util.List;
import com.phongvi.product.ProductStatus;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findAllByStatusAndNameContainingIgnoreCaseAndCategoriesInAndSupplierIn(
			ProductStatus status, String name, Collection<ProductCategory> categories, Collection<Supplier> supplier, Pageable pageable);
	
	Page<Product> findAllByStatusAndNameContainingIgnoreCaseAndSupplierIn(
			ProductStatus status, String name, Collection<Supplier> supplier, Pageable pageable);
	
	Page<Product> findAllByStatusAndNameContainingIgnoreCaseAndCategoriesIn(
			ProductStatus status, String name, Collection<ProductCategory> categories, Pageable pageable);
	
	Page<Product> findAllByStatusAndNameContainingIgnoreCase(
			ProductStatus status, String name, Pageable pageable);
	
	Page<Product> findAllByNameContainingIgnoreCaseAndSupplierIn(
			String name, Collection<Supplier> supplier, Pageable pageable);
	
	Page<Product> findAllByNameContainingIgnoreCaseAndCategoriesIn(
			String name, Collection<ProductCategory> categories, Pageable pageable);
	
	Page<Product> findAllByNameContainingIgnoreCaseAndCategoriesInAndSupplierIn(
			String name, Collection<ProductCategory> categories, Collection<Supplier> supplier, Pageable pageable);
	
	Page<Product> findAllByNameContainingIgnoreCase(
			String name, Pageable pageable);
	
	Optional<Product> findByIdAndStatus(Long id, ProductStatus status);
}
