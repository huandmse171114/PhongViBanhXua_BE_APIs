package com.phongvi.product_category;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale.Category;

import com.phongvi.product.Product;
import com.phongvi.supplier.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_productcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			unique = true,
			length = 100)
	private String name;
	
	private String description;
	
	private String categoryImg;
	
	@Column(nullable = false)
	private ProductCategoryStatus status;
	
	@Column(nullable = false,
			updatable = false)
	private Timestamp createdAt;
	
	@Column(nullable = false,
			updatable = false,
			length = 50)
	private String createdBy;
	
	@Column(nullable = false)
	private Timestamp lastChangedAt;
	
	@Column(nullable = false,
			length = 50)
	private String lastChangedBy;
	
	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private ProductCategory parentCategory;
	
	@ManyToMany(mappedBy = "categories")
	private List<Product> products;
	
	@ManyToMany(mappedBy = "categories")
	private List<Supplier> suppliers;
}
