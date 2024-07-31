package com.phongvi.blog;

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
@Table(name = "tbl_blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			length = 300)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false, 
			length = 2000)
	private String content;
	
	@Column(nullable = false)
	private String blogPreviewImg;
	
	@Column(nullable = false,
			length = 50)
	private BlogStatus status;
	
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
