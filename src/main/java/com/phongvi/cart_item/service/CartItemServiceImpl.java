package com.phongvi.cart_item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemRepository;
import com.phongvi.cart_item.CartItemStatus;
import com.phongvi.cart_item.CartItemType;
import com.phongvi.cart_item.dto.CartItemCreateUpdateBodyDTO;
import com.phongvi.cart_item.dto.CartItemResponseDTO;
import com.phongvi.combo.Combo;
import com.phongvi.combo.ComboRepository;
import com.phongvi.customer.Customer;
import com.phongvi.customer.CustomerRepository;
import com.phongvi.exception.NoCartItemFoundException;
import com.phongvi.exception.NoComboFoundException;
import com.phongvi.exception.NoCustomerFoundException;
import com.phongvi.exception.NoProductFoundException;
import com.phongvi.exception.NoUserFoundException;
import com.phongvi.product.Product;
import com.phongvi.product.ProductRepository;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import com.phongvi.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
	private final CartItemMappingService mappingService;
	private final CartItemRepository repository;
	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final ComboRepository comboRepository;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150; 
	
	@Override
	public ResponseEntity<?> getAllCartItemByStatus(CartItemStatus active, Integer page, Integer size,
			String username) {
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		
		if (userOptional.isEmpty()) {
			throw new NoUserFoundException("Lấy giỏ hàng thất bại: không tìm thấy người dùng");
		}
		
		Optional<Customer> customerOptional = customerRepository.findByUser(userOptional.get());
		
		if (customerOptional.isEmpty()) {
			throw new NoCustomerFoundException("Lấy giỏ hàng thất bại: chỉ tài khoản khách hàng mới có thể xem giỏ hàng");
		}
		
		Customer customer = customerOptional.get();
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<CartItem> pageCartItems = repository.findAllByCustomer(customer, paging);
		
		List<CartItem> cartItems = pageCartItems.getContent();
		
		if (cartItems.isEmpty()) {
			throw new NoCartItemFoundException("Giỏ hàng đang rỗng");
		}
		
		List<CartItemResponseDTO> response = cartItems.stream()
				.map(item -> mappingService.cartItemToCartItemResponseDTO(item))
				.toList();
		
		return Utils.generatePagingListResponseEntity(
				pageCartItems.getTotalElements(),
				response, 
				pageCartItems.getTotalPages(), 
				pageCartItems.getNumber(), 
				HttpStatus.OK);
	}
	@Override
	public String modifyCartItem(@Valid CartItemCreateUpdateBodyDTO cartItemDTO, String username) {
		
		String message = "";
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		
		if (userOptional.isEmpty()) {
			throw new NoUserFoundException("Thêm vào giỏ hàng thất bại: không tìm thấy người dùng");
		}
		
		Optional<Customer> customerOptional = customerRepository.findByUser(userOptional.get());
		
		if (customerOptional.isEmpty()) {
			throw new NoCustomerFoundException("Thêm vào giỏ hàng thất bại: chỉ tài khoản khách hàng mới có thể thực hiện");
		}
		
		Customer customer = customerOptional.get();
		
		if (cartItemDTO.type() == CartItemType.COMBO) {
			
			Optional<Combo> comboOptional = comboRepository.findById(cartItemDTO.comboId());
			
			if (comboOptional.isEmpty()) {
				throw new NoComboFoundException("Thêm vào giỏ hàng thất bại: không tìm thấy combo");
			}
			
			Combo combo = comboOptional.get();
			
		}else {
			
			Optional<Product> productOptional = productRepository.findById(cartItemDTO.productId());
			
			if (productOptional.isEmpty()) {
				throw new NoProductFoundException("Thêm vào giỏ hàng thất bại: không tìm thấy sản phẩm");
			}
			
			Product product = productOptional.get();
			
			Optional<CartItem> cartItemOptional = repository.findByCustomerAndProduct(customer, product);
			
			if (cartItemOptional.isEmpty()) {
				// Cart item chua co san trong database -> tao moi
				if (cartItemDTO.quantity() > 0) {
					// Quantity hop le
					CartItem newCartItem = mappingService.cartItemDTOToCartItem(customer, cartItemDTO.quantity(), product, null, cartItemDTO.type());
					
					repository.save(newCartItem);
					
					message = "Thêm vào giỏ hàng thành công";
					
				} else message = "Cập nhật giỏ hàng thất bại: số lượng không hợp lệ";
				
			} else {
				// Cart item co san trong database -> cap nhat quantity
				CartItem cartItem = cartItemOptional.get();
				
				if (cartItemDTO.quantity() > 0) {
					// Cap nhat quantity
					cartItem.setQuantity(cartItemDTO.quantity());
					cartItem.setLastChangedAt(Utils.getCurrentTimestamp());
					cartItem.setStatus(CartItemStatus.ACTIVE); // Cap nhat lai status cho item inactive
					
					repository.save(cartItem);
					
					message = "Cập nhật giỏ hàng thành công";
				
				} else if (cartItemDTO.quantity() == 0) {
					// Xoa cart item
					cartItem.setQuantity(cartItemDTO.quantity());
					cartItem.setLastChangedAt(Utils.getCurrentTimestamp());
					cartItem.setStatus(CartItemStatus.INACTIVE); // Xoa item
					
					repository.save(cartItem);
					
					message = "Xóa khỏi giỏ hàng thành công";
					
				} else message = "Cập nhật giỏ hàng thất bại: số lượng không hợp lệ";
			}
			
		}
		
		return message;
	}
	
	
}
