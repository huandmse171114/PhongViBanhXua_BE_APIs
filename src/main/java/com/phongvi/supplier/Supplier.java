package com.phongvi.supplier;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.product.Product;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			unique = true,
			length = 100)
	private String name;
	
	private String description;
	
	@Column(nullable = false,
			length = 50)
	private String ownerName;
	
	@Column(nullable = false,
			unique = true,
			length = 12)
	private String ownerId;
	
	@Column(nullable = false,
			unique = true,
			length = 10)
	private String phone;
	
	@Column(nullable = false)
	private Time openedTime;
	
	@Column(nullable = false)
	private Time closedTime;
	
//	address must be unique too
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String ward;
	
	@Column(nullable = false)
	private String district;

	@Column(nullable = false)
	private String province;

	@Column(nullable = false)
	private String wardCode;

	@Column(nullable = false)
	private Integer districtId;

	@Column(nullable = false)
	private Integer provinceId;
	
	private String shopSurroundingImg1;

	private String shopSurroundingImg2;
	
	private String shopSurroundingImg3;

	@Column(nullable = false)
	private int totalRating;

	@Column(nullable = false)
	private double averageStar;

	@Column(nullable = false,
			length = 50)
	private SupplierStatus status;
	
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
	
	@OneToMany(mappedBy = "supplier")
	private List<Product> products;
	
	@ManyToMany
	@JoinTable(
			name = "tbl_supplier_productcategory",
			joinColumns = @JoinColumn(name = "supplier_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "productcategory_id", nullable = false)
	)
	private List<ProductCategory> categories;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}



