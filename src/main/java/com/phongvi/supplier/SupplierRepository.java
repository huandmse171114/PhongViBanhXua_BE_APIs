package com.phongvi.supplier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongvi.product_category.ProductCategory;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
	Page<Supplier> findAllByStatusAndNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCaseAndCategoriesIn(
				SupplierStatus status, String name, String street,
				String ward, String district, String province, 
				Collection<ProductCategory> categories, Pageable pageable
			);
	
	Page<Supplier> findAllByNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCaseAndCategoriesIn(
			String name, String street,
			String ward, String district, String province, 
			Collection<ProductCategory> categories, Pageable pageable
		);
	
	Page<Supplier> findAllByStatusAndNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCase(
			SupplierStatus status, String name, String street,
			String ward, String district, String province, 
			Pageable pageable
		);

	Page<Supplier> findAllByNameContainingAndStreetContainingAndWardContainingAndDistrictContainingAndProvinceContainingAllIgnoreCase(
		String name, String street,
		String ward, String district, String province, 
		Pageable pageable
	);
	
	List<Supplier> findAllByNameContainingIgnoreCase(String name);
}
