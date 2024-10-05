package com.phongvi.transaction;

import java.sql.Timestamp;

import com.phongvi.order.Order;
import com.phongvi.wallet.Wallet;

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
@Table(name = "tbl_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long amount;
	
	private String description;
	
	@Column(nullable = false,
			length = 50)
	private TransactionType type;
	
	@Column(nullable = false,
			length = 50)
	private TransactionStatus status;
	
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
	@JoinColumn(name = "towallet_id")
	private Wallet toWallet;
	
	@ManyToOne
	@JoinColumn(name = "fromwallet_id")
	private Wallet fromWallet;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
}
