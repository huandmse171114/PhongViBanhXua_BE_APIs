package com.phongvi.comment;

import java.sql.Timestamp;

import com.phongvi.customer.Customer;
import com.phongvi.order_item.OrderItem;

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
@Table(name = "tbl_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer star;
	
	private String comment;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private CommentStatus status;
	
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
	@JoinColumn(name = "orderitem_id")
	private OrderItem orderItem;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
