package com.phongvi.combo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.phongvi.combo_item.ComboItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_Combo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Combo {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			length = 200)
	private String name;
	
	private String description;
	
	@Column(nullable = false)
	private Long price;
	
	private Long discountPrice;
	
	private Date discountExpiry;
	
	@Column(nullable = false)
	private int totalRating;
	
	@Column(nullable = false)
	private double averageStar;
	
	@Column(nullable = false)
	private Integer dailyStock;
	
	@Column(nullable = false,
			length = 50)
	@Enumerated(EnumType.STRING)
	private ComboStatus status;
	
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
	
	@OneToMany(mappedBy = "combo")
	private List<ComboItem> items;
}
