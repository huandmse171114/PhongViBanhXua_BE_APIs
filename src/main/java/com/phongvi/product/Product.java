package com.phongvi.product;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_image.ProductImage;
import com.phongvi.supplier.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	@Column(nullable = false)
	private Long price;
	
	private Long discountPrice;
	
	private Date discountExpiry;
	
	private Integer calories;
	
	@Column(nullable = false)
	private int totalRating;
	
	@Column(nullable = false)
	private double averageStar;
	
	@Column(nullable = false)
	private Integer dailyStock;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
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
	
	@OneToMany(mappedBy = "product")
	private List<ProductImage> images;
	
	@ManyToMany
	@JoinTable(
			name = "tbl_product_productcategory",
			joinColumns = @JoinColumn(name = "product_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "productcategory_id", nullable = false)
	)
	private List<ProductCategory> categories;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
}




