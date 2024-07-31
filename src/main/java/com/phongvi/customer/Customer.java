package com.phongvi.customer;

import java.sql.Date;
import java.sql.Timestamp;

import com.phongvi.user.Gender;

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
}
