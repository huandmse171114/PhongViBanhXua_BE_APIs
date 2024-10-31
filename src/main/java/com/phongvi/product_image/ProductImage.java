package com.phongvi.product_image;

import java.sql.Timestamp;

import com.phongvi.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_productimg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer index;
	
	@Column(nullable = false,
			length = 500)
	private String source;
	
	private String description;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private ProductImageStatus status;
	
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
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return status.toString() + source;
	}
}
