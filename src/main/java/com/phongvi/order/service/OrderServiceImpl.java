package com.phongvi.order.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.CartItem;
import com.phongvi.cart_item.CartItemRepository;
import com.phongvi.cart_item.CartItemStatus;
import com.phongvi.cart_item.CartItemType;
import com.phongvi.customer.Customer;
import com.phongvi.customer.CustomerRepository;
import com.phongvi.exception.NoCartItemFoundException;
import com.phongvi.exception.NoCustomerFoundException;
import com.phongvi.exception.NoUserFoundException;
import com.phongvi.order.Order;
import com.phongvi.order.OrderRepository;
import com.phongvi.order.OrderShippingType;
import com.phongvi.order.dto.OrderCreateDTO;
import com.phongvi.order_item.OrderItem;
import com.phongvi.order_item.OrderItemRepository;
import com.phongvi.order_item.service.OrderItemMappingService;
import com.phongvi.payos.service.PayOSService;
import com.phongvi.shipment.ShipmentRepository;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import com.phongvi.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.payos.type.CheckoutResponseData;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository repository;
	private final OrderMappingService mappingService;
	private final UserRepository userRepository;
	private final CartItemRepository cartItemRepository;
	private final CustomerRepository customerRepository;
	private final OrderItemMappingService itemMappingService;
	private final OrderItemRepository itemRepository;
	private final PayOSService payOSService;
	private final int DEFAULT_PAGE = 0;
	private final int DEFALT_SIZE = 150;
	private Long orderAmount;
	
	@Override
	public ResponseEntity<?> createOrder(String username, @Valid OrderCreateDTO orderDTO) {
		Customer customer = getCustomer(username);
		
		Order order = mappingService.orderCreateDTOToOrder(orderDTO);
		
		List<CartItem> cartItems = cartItemRepository.findAllByStatusAndIdIn(CartItemStatus.ACTIVE, orderDTO.items());
		
		if (cartItems.size() < orderDTO.items().size()) {
			throw new NoCartItemFoundException("Sản phẩm cần thanh toán không có trong giỏ hàng.");
		}
		
		order.setCustomer(customer);
		
		orderAmount = 0L;
		
		cartItems.forEach(item -> {
			if (item.getType() == CartItemType.PRODUCT) {
				if (item.getProduct().getDiscountExpiry().after(Date.valueOf(LocalDate.now()))) {
					orderAmount += 
							item.getQuantity() * item.getProduct().getDiscountPrice();				
				} else {
					orderAmount += 
							item.getQuantity() * item.getProduct().getPrice();
				}				
			} else {
				if (item.getCombo().getDiscountExpiry().after(Date.valueOf(LocalDate.now()))) {
					orderAmount += 
							item.getQuantity() * item.getCombo().getDiscountPrice();				
				} else {
					orderAmount += 
							item.getQuantity() * item.getCombo().getPrice();
				}	
			}
			
		});
		
		order.setOrderAmount(orderAmount);
		order.setTotalAmount(orderAmount + order.getShippingFee());
		
		// luu don thanh toan o trang thai cho thanh toan
		Order orderDB = repository.save(order);
		
		cartItems.forEach(item -> {
			// Kiem tra so luong ton kho
			// ...
			
			OrderItem orderItem = itemMappingService.cartItemToOrderItem(item);
			orderItem.setOrder(orderDB);
			itemRepository.save(orderItem);
		});
		
		// lay payment url
		CheckoutResponseData checkoutResponse = payOSService.createPaymentLink(orderDB, cartItems);
		
		return Utils.generateObjectResponseEntity(checkoutResponse, HttpStatus.OK);
		
	}
	
	private Customer getCustomer(String username) {
		
		Optional<User> userOption = userRepository.findByUsername(username);
		
		if (userOption.isEmpty()) {
			throw new NoUserFoundException("Người dùng không tồn tại.");
		}
		
		Optional<Customer> customerOption = customerRepository.findByUser(userOption.get());
		
		if (customerOption.isEmpty()) {
			throw new NoCustomerFoundException("Khách hàng không tồn tại.");
		}
		
		return customerOption.get();
	}
	
}
