package com.phongvi.shipment;

import java.sql.Timestamp;

import com.phongvi.customer.Customer;

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
@Table(name = "tbl_shipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private boolean isDefault;
	
	@Column(nullable = false,
			length = 100)
	private String receiverName;
	
	@Column(nullable = false,
			length = 10)
	private String contactNumber;
	
	private String description;
	
	@Column(nullable = false,
			length = 50)
	private String district;
	
	@Column(nullable = false)
	private Integer districtId;
	
	@Column(nullable = false,
			length = 50)
	private String ward;
	
	@Column(nullable = false,
			length = 20)
	private String wardCode;
	
	@Column(nullable = false,
			length = 50)
	private String province;
	
	@Column(nullable = false)
	private Integer provinceId;
	
	@Column(nullable = false,
			length = 100)
	private String street;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;
	
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
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
