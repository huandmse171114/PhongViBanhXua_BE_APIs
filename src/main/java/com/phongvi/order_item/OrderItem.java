package com.phongvi.order_item;

import java.sql.Timestamp;
import java.util.List;

import com.phongvi.combo.Combo;
import com.phongvi.comment.Comment;
import com.phongvi.order.Order;
import com.phongvi.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_orderitem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Long itemPrice;
	
	@Column(nullable = false,
			length = 50)
	private OrderItemType type;
	
	@Column(nullable = false,
			length = 50)
	private OrderItemStatus status;
	
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
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "combo_id")
	private Combo combo;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "orderItem")
	private List<Comment> comments;
}
