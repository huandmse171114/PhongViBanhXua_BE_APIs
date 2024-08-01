package com.phongvi.cart_item;

import java.sql.Timestamp;

import com.phongvi.cart.Cart;
import com.phongvi.combo.Combo;
import com.phongvi.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_cartitem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false,
			length = 50)
	private CartItemType type;

	@Column(nullable = false,
			length = 50)
	private CartItemStatus status;
	
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
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = true)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "combo_id", nullable = true)
	private Combo combo;
}
