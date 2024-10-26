package com.phongvi.shipment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.customer.Customer;
import com.phongvi.customer.CustomerRepository;
import com.phongvi.exception.DefaultShipmentUpdateException;
import com.phongvi.exception.NoCustomerFoundException;
import com.phongvi.exception.NoShipmentFoundException;
import com.phongvi.exception.NoUserFoundException;
import com.phongvi.shipment.Shipment;
import com.phongvi.shipment.ShipmentRepository;
import com.phongvi.shipment.ShipmentStatus;
import com.phongvi.shipment.dto.ShipmentCreateDTO;
import com.phongvi.shipment.dto.ShipmentUpdateDTO;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import com.phongvi.utils.Utils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
	private final ShipmentRepository repository;
	private final ShipmentMappingService mappingService;
	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_VALUE = 150;
	
	@Override
	public ResponseEntity<?> getAllShipmentByStatus(ShipmentStatus status, String username, Integer page,
			Integer size) {
		Customer customer = getCustomer(username);
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_VALUE;
		
		Pageable paging = PageRequest.of(page, size);
		
		var pageShipments = repository.findAllByStatusAndCustomer(status, customer, paging);
		
		var shipments = pageShipments.getContent();
		
		if (shipments.isEmpty()) {
			throw new NoShipmentFoundException("Dữ liệu về thông tin giao hàng hiện đang rỗng");
		}
		
		var response = shipments.stream()
				.map(shipment -> mappingService.shipmentToShipmentResponseDTO(shipment))
				.toList();
		
		return Utils.generatePagingListResponseEntity(
				pageShipments.getTotalElements(), 
				response, 
				pageShipments.getTotalPages(), 
				pageShipments.getNumber(), 
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<?> getDefaultShipment(String username) {
		Customer customer = getCustomer(username);
		
		Optional<Shipment> defaultShipmentOptional = repository.findByCustomerAndIsDefault(customer, true);
		
		if (defaultShipmentOptional.isEmpty()) {
			throw new NoShipmentFoundException("Người dùng chưa có thông tin địa chỉ nhận hàng.");
		}
		
		var response = new HashMap<String, Object>();
		
		response.put("data", mappingService.shipmentToShipmentResponseDTO(defaultShipmentOptional.get()));
		response.put("status", HttpStatus.OK.value());
		response.put("timestamp", Utils.getCurrentTimestamp());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Override
	public void saveShipment(String username, @Valid ShipmentCreateDTO shipmentDTO) {
		Customer customer = getCustomer(username);
		
		Shipment shipment = mappingService.shipmentCreateDTOToShipment(shipmentDTO);
		
		Optional<Shipment> defaultShipmentOptional = repository.findByCustomerAndIsDefault(customer, true);
		
		if (shipment.isDefault()) {
			if (!defaultShipmentOptional.isEmpty()) {
				Shipment defaultShipment = defaultShipmentOptional.get();
				defaultShipment.setDefault(false);
				defaultShipment.setLastChangedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName());
				defaultShipment.setLastChangedAt(Utils.getCurrentTimestamp());
				repository.save(defaultShipment);
			}			
		}else {
			if (defaultShipmentOptional.isEmpty()) {
				shipment.setDefault(true);
			}
		}
		
		shipment.setCustomer(customer);
		
		repository.save(shipment);
		
	}
	@Override
	public void updateShipment(String username, ShipmentUpdateDTO shipmentDTO) {
		Customer customer = getCustomer(username);
		
		Optional<Shipment> shipmentOption = repository.findById(shipmentDTO.id());
		
		if (shipmentOption.isEmpty()) {
			throw new NoShipmentFoundException("Không tìm thấy thông tin vận chuyển cần chỉnh sửa");
		}
		
		if (shipmentDTO.status() == null) {
			throw new NoShipmentFoundException("không được để trống thông tin status của thông tin giao hàng.");
		}
		
		Shipment shipment = shipmentOption.get();

//		Need to update default shipment
		if (shipment.isDefault() != shipmentDTO.isDefault()) {
//			Update to be default shipment
			if (shipmentDTO.isDefault()) {
				Optional<Shipment> defaultShipmentOptional = repository.findByCustomerAndIsDefault(customer, true);
				
//				Check if default shipment existed
				if (!defaultShipmentOptional.isEmpty()) {
					Shipment defaultShipment = defaultShipmentOptional.get();
					defaultShipment.setDefault(false);
					defaultShipment.setLastChangedAt(Utils.getCurrentTimestamp());
					defaultShipment.setLastChangedBy(SecurityContextHolder.getContext()
							.getAuthentication().getName());
					repository.save(defaultShipment);
				}
//			Update to not be default shipment
			}else {
				List<Shipment> newDefaultShipmentOptional = repository.findAllByCustomerAndStatusAndIdNot(customer, ShipmentStatus.ACTIVE, shipment.getId());
				
//				If there is one another shipment
				if (!newDefaultShipmentOptional.isEmpty()) {
					Shipment newDefaultShipment = newDefaultShipmentOptional.get(0);
					newDefaultShipment.setDefault(true);
					newDefaultShipment.setLastChangedAt(Utils.getCurrentTimestamp());
					newDefaultShipment.setLastChangedBy(SecurityContextHolder.getContext()
							.getAuthentication().getName());
					repository.save(newDefaultShipment);
					
//				If there is not another shipment
				}else {
					throw new DefaultShipmentUpdateException("Không thể thay đổi địa chỉ mặc định. Địa chỉ này là duy nhất.");
				}
			}
		}
		
		shipment.setReceiverName(shipmentDTO.receiverName());
		shipment.setContactNumber(shipmentDTO.contactNumber());
		shipment.setDescription(shipmentDTO.description());
		shipment.setStreet(shipmentDTO.street());
		shipment.setWard(shipmentDTO.ward());
		shipment.setWardCode(shipmentDTO.wardCode());
		shipment.setDistrict(shipmentDTO.district());
		shipment.setDistrictId(shipmentDTO.districtId());
		shipment.setDefault(shipmentDTO.isDefault());
		shipment.setStatus(shipmentDTO.status());
		shipment.setLastChangedAt(Utils.getCurrentTimestamp());
		shipment.setLastChangedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		repository.save(shipment);
		
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
	@Override
	public ResponseEntity<?> updateShipmentStatus(String username, Long id, ShipmentStatus status) {
		Customer customer = getCustomer(username);
		
		Optional<Shipment> shipmentOption = repository.findById(id);
		
		if (shipmentOption.isEmpty()) {
			throw new NoShipmentFoundException("Không tìm thấy thông tin vận chuyển cần xóa");
		}
		
		Shipment shipment = shipmentOption.get();
		
		if (status == ShipmentStatus.INACTIVE) {
			
			List<Shipment> newDefaultShipmentOptional = repository.findAllByCustomerAndStatusAndIdNot(customer, ShipmentStatus.ACTIVE, shipment.getId());
			

//			If there is only one shipment left or this current shipment is default
			if (!newDefaultShipmentOptional.isEmpty() &&
					(newDefaultShipmentOptional.size() == 1
					|| shipment.isDefault())) {
				Shipment newDefaultShipment = newDefaultShipmentOptional.get(0);
				newDefaultShipment.setDefault(true);
				newDefaultShipment.setLastChangedAt(Utils.getCurrentTimestamp());
				newDefaultShipment.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				repository.save(newDefaultShipment);
			}
			
			shipment.setStatus(status);
			shipment.setDefault(false);
			shipment.setLastChangedAt(Utils.getCurrentTimestamp());
			shipment.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			
			repository.save(shipment);

		}else {
			shipment.setStatus(status);
			shipment.setDefault(false);
			shipment.setLastChangedAt(Utils.getCurrentTimestamp());
			shipment.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			
			repository.save(shipment);
		}
		
		return Utils.generateMessageResponseEntity("Cập nhật thông tin giao hàng thành công.", HttpStatus.OK);
		
	}
	
	
}
