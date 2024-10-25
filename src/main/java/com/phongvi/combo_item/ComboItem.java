package com.phongvi.combo_item;

import java.sql.Timestamp;

import com.phongvi.combo.Combo;
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
@Table(name = "tbl_comboitem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComboItem {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private ComboItemStatus status;
	
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
	@JoinColumn(name = "combo_id")
	private Combo combo;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
