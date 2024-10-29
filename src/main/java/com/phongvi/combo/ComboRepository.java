package com.phongvi.combo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComboRepository extends JpaRepository<Combo, Long> {
	Page<Combo> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Combo> findAllByStatusAndNameContainingIgnoreCase(ComboStatus status, String name, Pageable pageable);
}
