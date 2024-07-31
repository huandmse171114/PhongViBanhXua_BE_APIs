package com.phongvi.wallet;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	
	@Column(nullable = false,
			unique = true,
			length = 100)
	private String LinkedAccountNumber;
	
	@Column(nullable = false,
			length = 100)
	private String LinkedAccountOwnerName;
	
	@Column(unique = true)
	private String checksumKey;
	
	@Column(nullable = false,
			length = 50)
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
}
