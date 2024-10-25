package com.phongvi.shipper;

import java.sql.Date;
import java.sql.Timestamp;

import com.phongvi.user.Gender;
import com.phongvi.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_shipper")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipper {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			length = 150)
	private String fullname;
	
	@Column(nullable = false,
			unique = true,
			length = 12)
	private String idNumber;
	
	@Column(nullable = true,
			unique = true,
			length = 10)
	private String phone;
	
	@Column(nullable = false,
			unique = true,
			length = 100)
	private String email;
	
	private Date dateOfBirth;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false)
	private String profileImg;
	
	@Column(nullable = false)
	private String vehicleBrand;
	
	@Column(nullable = false,
			unique = true)
	private String licensePlate;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private ShipperStatus status;
	
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
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
