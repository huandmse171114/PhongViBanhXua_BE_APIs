package com.phongvi.combo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.phongvi.combo.ComboStatus;
import com.phongvi.combo.dto.ComboCreateDTO;

import jakarta.validation.Valid;

public interface ComboService {

	ResponseEntity<?> getAllComboByStatus(ComboStatus active, Integer page, 
			Integer size, String name, boolean isAdminCalled);

	ResponseEntity<?> saveCombo(ComboCreateDTO comboDTO);

}
