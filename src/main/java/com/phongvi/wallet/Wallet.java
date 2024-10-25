package com.phongvi.wallet;

import java.sql.Timestamp;
import java.util.List;

import com.phongvi.customer.Customer;
import com.phongvi.transaction.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private int balance;
	
	@Column(
			unique = true,
			length = 100)
	private String LinkedAccountNumber;
	
	@Column(
			length = 100)
	private String LinkedAccountOwnerName;
	
	@Column(unique = true)
	private String checksumKey;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private WalletStatus status;
	
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
	
	@OneToOne(mappedBy = "wallet")
	private Customer customer;
	
	@OneToMany(mappedBy = "toWallet")
	private List<Transaction> receivedTransaction;
	
	@OneToMany(mappedBy = "fromWallet")
	private List<Transaction> sentTransaction;
}
