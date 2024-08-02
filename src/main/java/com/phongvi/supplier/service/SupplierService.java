package com.phongvi.supplier.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.phongvi.supplier.SupplierStatus;
import com.phongvi.supplier.dto.SupplierCreateDTO;
import com.phongvi.supplier.dto.SupplierUpdateDTO;

public interface SupplierService {

	ResponseEntity<?> getAllSupplierByStatus(SupplierStatus status, Integer page, Integer size, String street, String ward, String district,
			String province, String name, List<Long> categories, boolean isAdminCalled);

	ResponseEntity<?> updateSupplierStatus(SupplierStatus status, Long id);

	ResponseEntity<?> saveSupplier(SupplierCreateDTO supplierDTO);

	ResponseEntity<?> updateSupplier(Long id, SupplierUpdateDTO supplierDTO);

}
 