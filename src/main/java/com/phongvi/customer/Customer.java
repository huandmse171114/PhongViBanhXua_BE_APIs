package com.phongvi.customer;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.cart.Cart;
import com.phongvi.comment.Comment;
import com.phongvi.order.Order;
import com.phongvi.shipment.Shipment;
import com.phongvi.user.Gender;
import com.phongvi.user.User;
import com.phongvi.wallet.Wallet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			length = 150)
	private String fullname;
	
	@Column(nullable = false,
			unique = true,
			length = 10)
	private String phone;
	
	private Date dateOfBirth;
	
	@Column(length = 50)
	private Gender gender;
	
	private String profileImg;
	
	@Column(nullable = false,
			length = 50)
	private CustomerStatus status;
	
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
	
	@OneToOne(mappedBy = "customer")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "customer")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "customer")
	private List<Shipment> shipments;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	@OneToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
}
