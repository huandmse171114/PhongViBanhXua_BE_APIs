package com.phongvi.user.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
import com.phongvi.utils.Utils;
import com.phongvi.wallet.Wallet;
import com.phongvi.wallet.WalletRepository;
import com.phongvi.wallet.WalletStatus;

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
	private final WalletRepository walletRepository;
	
	
	@Override
	public User createUser(User user) {
		User userDB = repository.save(user);
		
		if (userDB.getRole() == UserRole.CUSTOMER) {
			Customer customer = customerMappingService.userToCustomer(userDB);
			
			Customer customerDB = customerRepository.save(customer);
			
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
			
			Wallet wallet = Wallet.builder()
					.customer(customerDB)
					.createdAt(Utils.getCurrentTimestamp())
					.createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.lastChangedAt(Utils.getCurrentTimestamp())
					.lastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName())
					.status(WalletStatus.ACTIVE)
					.build();
			
			walletRepository.save(wallet);
			
		}else if (userDB.getRole() == UserRole.SHIPPER) {
			Shipper shipper = shipperMappingService.userToShipper(user);
			
			shipperRepository.save(shipper);
		}
	}
	
	
}
