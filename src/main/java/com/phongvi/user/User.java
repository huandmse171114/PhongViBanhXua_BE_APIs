package com.phongvi.user;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,
			unique = true,
			length = 100) 
	private String username;
	
	private String password;
	
	@Column(nullable = false,
			unique = true,
			length = 150)
	private String email;
	
	@Column(length = 150)
	private String fullname;
	
	@Column(nullable = true,
			unique = true,
			length = 10)
	private String phone;
	
	private String profileImg;
	
	private String resetPwdToken;
	
	private Timestamp resetPwdExpiry;
	
	@Column(nullable = false,
			length = 50)
	private Provider provider;
	
	@Column(nullable = false,
			length = 50)
	private UserStatus status;
	
	@Column(nullable = false,
			length = 50)
	private UserRole role;
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.status == UserStatus.ACTIVE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.status == UserStatus.ACTIVE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.status == UserStatus.ACTIVE;
	}

	@Override
	public boolean isEnabled() {
		return this.status == UserStatus.ACTIVE;
	}
	
	
}
