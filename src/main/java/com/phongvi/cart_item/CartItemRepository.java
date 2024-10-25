package com.phongvi.cart_item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongvi.customer.Customer;
import java.util.List;
import com.phongvi.product.Product;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Page<CartItem> findAllByCustomer(Customer customer, Pageable pageable);
	
	Optional<CartItem> findByCustomerAndProduct(Customer customer, Product product);
}
