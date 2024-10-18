package com.phongvi.user.service;

import org.springframework.stereotype.Service;

import com.phongvi.cart.Cart;
import com.phongvi.cart.CartRepository;
import com.phongvi.customer.Customer;
import com.phongvi.customer.CustomerRepository;
import com.phongvi.customer.service.CustomerMappingService;
import com.phongvi.exception.PasswordConfirmIsDifferentException;
import com.phongvi.shipper.Shipper;
import com.phongvi.shipper.ShipperRepository;
import com.phongvi.shipper.service.ShipperMappingService;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import com.phongvi.user.UserRole;
import com.phongvi.user.dto.UserCreateDTO;
import com.phongvi.wallet.Wallet;
import com.phongvi.wallet.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final UserMappingService mappingService;
	private final CustomerMappingService customerMappingService;
	private final ShipperMappingService shipperMappingService;
	private final CustomerRepository customerRepository;
	private final ShipperRepository shipperRepository;
	private final CartRepository cartRepository;
	private final WalletRepository walletRepository;
	
	
	@Override
	public User createUser(User user) {
		User userDB = repository.save(user);
		
		if (userDB.getRole() == UserRole.CUSTOMER) {
			Customer customer = customerMappingService.userToCustomer(userDB);
			
			Customer customerDB = customerRepository.save(customer);
			
			Cart cart = Cart.builder()
					.customer(customerDB)
					.build();
			
			cartRepository.save(cart);
			
			Wallet wallet = Wallet.builder()
					.customer(customerDB)
					.build();
			
			walletRepository.save(wallet);
			
		}else if (userDB.getRole() == UserRole.SHIPPER) {
			Shipper shipper = shipperMappingService.userToShipper(user);
			
			shipperRepository.save(shipper);
		}
		
		return userDB;
	}

	@Override
	public void registerUser(UserCreateDTO userDTO) {
		if (!userDTO.confirmPassword().equals(userDTO.password())) {
			throw new PasswordConfirmIsDifferentException("Mật khẩu xác minh không chính xác.");
		}
		
		User user = mappingService.userCreateBodyDTOToUser(userDTO);
		
		User userDB = repository.save(user);
		
		if (userDB.getRole() == UserRole.CUSTOMER) {
			Customer customer = customerMappingService.userToCustomer(userDB);
			
			Customer customerDB = customerRepository.save(customer);
			
			Cart cart = Cart.builder()
					.customer(customerDB)
					.build();
			
			cartRepository.save(cart);
			
			Wallet wallet = Wallet.builder()
					.customer(customerDB)
					.build();
			
			walletRepository.save(wallet);
			
		}else if (userDB.getRole() == UserRole.SHIPPER) {
			Shipper shipper = shipperMappingService.userToShipper(user);
			
			shipperRepository.save(shipper);
		}
	}
	
	
}
