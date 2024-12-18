package com.phongvi.order;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.customer.Customer;
import com.phongvi.order_item.OrderItem;
import com.phongvi.transaction.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tbl_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = true)
	private Time expectedDeliveryTime;
	
	@Column(nullable = true,
			length = 50)
	private String district;
	
	@Column(nullable = true)
	private Integer districtId;
	
	@Column(nullable = true,
			length = 50)
	private String ward;
	
	@Column(nullable = true,
			length = 20)
	private String wardCode;
	
	@Column(nullable = true,
			length = 50)
	private String province;
	
	@Column(nullable = true)
	private Integer provinceId;
	
	@Column(nullable = true,
			length = 100)
	private String street;
	
	@Column(nullable = false)
	private Long shippingFee;
	
	@Column(nullable = false)
	private Long orderAmount;
	
	@Column(nullable = false)
	private Long totalAmount;
	
	@Column(nullable = false,
			length = 10)
	private String contactNumber;
	
	@Column(nullable = false,
			length = 100)
	private String receiverName;
	
	private Time deliveredTime;
	
	private Time pickupTime;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(nullable = false,
			length = 50)
	private OrderPaymentStatus paymentStatus;

	@Column(nullable = false,
			length = 50)
	private OrderPaymentType paymentType;
	
	@Column(nullable = false,
			length = 50)
	private OrderShippingType shippingType;
	
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
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private List<Transaction> transactions;
}
