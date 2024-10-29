package com.phongvi.shipment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongvi.customer.Customer;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	
	Optional<Shipment> findByCustomerAndIsDefault(Customer customer, boolean isDefault);
	
	Page<Shipment> findAllByStatusAndCustomer(ShipmentStatus status, Customer customer, Pageable pageable);
	
	List<Shipment> findAllByCustomerAndStatusAndIdNot(Customer customer, ShipmentStatus status, Long id);
}
