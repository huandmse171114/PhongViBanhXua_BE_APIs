package com.phongvi.product.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.exception.NoProductFoundException;
import com.phongvi.exception.NoProductImageFoundException;
import com.phongvi.exception.NoProductImageStatusFoundException;
import com.phongvi.exception.NoSupplierFoundException;
import com.phongvi.product.Product;
import com.phongvi.product.ProductRepository;
import com.phongvi.product.ProductStatus;
import com.phongvi.product.dto.ProductAdminResponseDTO;
import com.phongvi.product.dto.ProductCreateDTO;
import com.phongvi.product.dto.ProductImageProductUpdateDTO;
import com.phongvi.product.dto.ProductResponseDTO;
import com.phongvi.product.dto.ProductUpdateDTO;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.product_category.dto.ProductCategoryAdminResponseDTO;
import com.phongvi.product_image.ProductImage;
import com.phongvi.product_image.ProductImageRepository;
import com.phongvi.product_image.ProductImageStatus;
import com.phongvi.product_image.service.ProductImageMappingService;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierRepository;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	private final ProductMappingService mappingService;
	private final ProductImageMappingService imageMappingService;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150;
	private final ProductCategoryRepository categoryRepository;
	private final SupplierRepository supplierRepository;
	private final ProductImageRepository imageRepository;
	
	@Override
	public ResponseEntity<?> getAllProductByStatus(ProductStatus status, Integer page, Integer size, String name,
			List<Long> categories, String supplierName, boolean isAdminCalled) {
		
		if (page == null) page = DEFAULT_PAGE;
		if (size == null) size = DEFAULT_SIZE;
		
		Pageable paging = PageRequest.of(page, size);
		
		Page<Product> pageProducts;
		
		List<ProductCategory> categoriesDB; 
		
		if (categories == null) {
			categoriesDB = null;
		}else {
			categoriesDB = categoryRepository.findAllByIdIn(categories);
		}
		
		System.out.println(categoriesDB);
		
		if (categories != null && categories.size() != categoriesDB.size()) {
			throw new NoProductCategoryFoundException("Lấy thông tin sản phẩm thất bại: không tìm thấy một số danh mục tương ứng.");
		}
		
		List<Supplier> suppliers;
		
		if (!supplierName.isEmpty()) {
			suppliers = supplierRepository.findAllByNameContainingIgnoreCase(supplierName);			
		}else {
			suppliers = null;
		}
		
		System.out.println(suppliers);
		
		if (status == null) {
			// Get all product with ANY status
			if (suppliers != null && categoriesDB != null) {
				pageProducts = repository.findAllByNameContainingIgnoreCaseAndCategoriesInAndSupplierIn(
						name, categoriesDB, suppliers, paging);				
			}else if (suppliers == null && categoriesDB != null) {
				pageProducts = repository.findAllByNameContainingIgnoreCaseAndCategoriesIn(
						name, categoriesDB, paging);	
			}else if (suppliers != null && categoriesDB == null) {
				pageProducts = repository.findAllByNameContainingIgnoreCaseAndSupplierIn(
						name, suppliers, paging);	
			} else {
				pageProducts = repository.findAllByNameContainingIgnoreCase(
						name, paging);
			}
		}else {
			if (suppliers != null && categoriesDB != null) {
				pageProducts = repository.findAllByStatusAndNameContainingIgnoreCaseAndCategoriesInAndSupplierIn(
						status, name, categoriesDB, suppliers, paging);
			} else if (suppliers == null && categoriesDB != null) {
				pageProducts = repository.findAllByStatusAndNameContainingIgnoreCaseAndCategoriesIn(
						status, name, categoriesDB, paging);
			} else if (suppliers != null && categoriesDB == null) {
				pageProducts = repository.findAllByStatusAndNameContainingIgnoreCaseAndSupplierIn(
						status, name, suppliers, paging);
			}else {
				pageProducts = repository.findAllByStatusAndNameContainingIgnoreCase(
						status, name, paging);
			}
		}
		
		List<Product> products = pageProducts.getContent(); 
		
		if (products.isEmpty()) throw new NoProductFoundException("Không tìm thấy sản phẩm tương ứng.");
		
		products.forEach(product -> {
			// Filter active images only
			product.setImages(product.getImages().stream()
					.filter(image -> image.getStatus() == ProductImageStatus.ACTIVE)
					.toList());
			
			// Filter active categories only
			product.setCategories(product.getCategories().stream()
					.filter(cate -> cate.getStatus() == ProductCategoryStatus.ACTIVE)
					.toList());
		});
		
		List response;
		
		if (isAdminCalled) {
			// Mapping to admin response DTO
			response = products.stream()
					.map(product -> mappingService.productToProductAdminResponseDTO(product))
					.sorted(new Comparator<ProductAdminResponseDTO>() {
						@Override
						public int compare(ProductAdminResponseDTO o1, ProductAdminResponseDTO o2) {
							return o2.lastChangedAt().compareTo(o1.lastChangedAt());
						}
					})
					.toList();
		}else {
			// Mapping to store response DTO
			response = products.stream()
					.map(product -> mappingService.productToProductResponseDTO(product))
					.sorted(new Comparator<ProductResponseDTO>() {
						@Override
						public int compare(ProductResponseDTO o1, ProductResponseDTO o2) {
							return o2.name().compareTo(o1.name());
						}
					})
					.toList();
		}
		
		return Utils.generatePagingListResponseEntity(
				pageProducts.getTotalElements(), 
				response, 
				pageProducts.getTotalPages(), 
				pageProducts.getNumber(), 
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateProductStatus(ProductStatus status, Long id) {
		
		Optional<Product> productOptional = repository.findById(id);
		
		if (productOptional.isEmpty()) {
			throw new NoProductCategoryFoundException("Hiển thị sản phẩm thất bại: không tìm thấy sản phẩm tương ứng.");
		}
		
		Product product = productOptional.get();
		
		product.setStatus(status);
		product.setLastChangedAt(Utils.getCurrentTimestamp());
		product.setLastChangedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		repository.save(product);
		
		if (status == ProductStatus.ACTIVE) {
			return Utils.generateMessageResponseEntity("Hiển thị sản phẩm thành công!", HttpStatus.OK);
		}else if (status == ProductStatus.INACTIVE) {
			return Utils.generateMessageResponseEntity("Ẩn sản phẩm thành công!", HttpStatus.OK);
		}else {
			return Utils.generateMessageResponseEntity("Cập nhật trạng thái sản phẩm thành công!", HttpStatus.OK);
		}
		
	}

	@Override
	public ResponseEntity<?> saveProduct(ProductCreateDTO productDTO) {
		Product newProduct = mappingService.productCreateDTOToProduct(productDTO);
		
		Product productDB = repository.save(newProduct);
		
		// Return list of ProductImages that has generated ID
		List<ProductImage> imagesDB = new ArrayList<>();
		newProduct.getImages().forEach(image -> {
			System.out.println(image);
			image.setProduct(productDB);
			imagesDB.add(imageRepository.save(image));
		});
		
		return Utils.generateMessageResponseEntity("Tạo mới sản phẩm thành công", HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<?> updateProduct(Long id, ProductUpdateDTO productDTO) {
		
		// Get product from database
		Optional<Product> productOptional = repository.findById(id);
		
		// If not found, throw exception
		if (productOptional.isEmpty()) {
			throw new NoProductFoundException("Cập nhật thất bại: không tìm thấy sản phẩm tương ứng.");
		}
		
		Product product = productOptional.get();
		
		List<Long> categoriesId = productDTO.categories();
		
		// Get new categories
		List<ProductCategory> categories = categoryRepository.findAllByIdIn(categoriesId);
		
		// If some categories is not found, throw exception
		if (categories.size() != categoriesId.size()) {
			throw new NoProductCategoryFoundException("Cập nhật thất bại: không tìm thấy một số danh mục sản phẩm.");
		}
		
		// Get new supplier
		Optional<Supplier> supplierOptional = supplierRepository.findById(productDTO.supplier());
		
		// If supplier is not found, throw exception
		if (supplierOptional.isEmpty()) {
			throw new NoSupplierFoundException("Cập nhật thất bại: không tìm thấy nhà cung cấp.");
		}
		
		Supplier supplier = supplierOptional.get();
		
		List<ProductImageProductUpdateDTO> images = productDTO.images();
		
		// Update product images
		images.forEach(image -> {
			if (image.id() == 0) {
				// id = 0 -> create new image
				ProductImage newImage = imageMappingService.productImageProductUpdateDTOToProductImage(image);
				newImage.setProduct(product);
				
				imageRepository.save(newImage);
				
			}else {
				// id != 0 -> update image
				
				if (image.status() == null) {
					throw new NoProductImageFoundException("Cập nhật ảnh thất bại: vui lòng cung cấp trạng thái ảnh sản phẩm");
				}
				
				Optional<ProductImage> imageDBOptional = imageRepository.findById(image.id());
				
				if (imageDBOptional.isEmpty()) {
					throw new NoProductImageStatusFoundException("Cập nhật thất bại: không tìm thấy ảnh sản phẩm.");
				}
				
				ProductImage imageDB = imageDBOptional.get();
				
				imageDB.setIndex(image.index());
				imageDB.setSource(image.source());
				imageDB.setDescription(image.description());
				imageDB.setLastChangedAt(Utils.getCurrentTimestamp());
				imageDB.setLastChangedBy(SecurityContextHolder.getContext()
						.getAuthentication().getName());
				imageDB.setStatus(image.status());
				
				imageRepository.save(imageDB);
				
			}
		});
		
		product.setName(productDTO.name());
		product.setDescription(productDTO.description());
		product.setPrice(productDTO.price());
		product.setDiscountPrice(productDTO.discountPrice());
		product.setDiscountExpiry(productDTO.discountExpiry());
		product.setCalories(productDTO.calories());
		product.setDailyStock(productDTO.dailyStock());
		product.setCategories(categories);
		product.setSupplier(supplier);
		product.setLastChangedAt(Utils.getCurrentTimestamp());
		product.setLastChangedBy(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		repository.save(product);
		
		return Utils.generateMessageResponseEntity("Cập nhật thông tin sản phẩm thành công", HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> getProductByIdAndStatus(ProductStatus status, Long id) {
		Optional<Product> productOptional;
		
		if (status == null) {
			productOptional = repository.findById(id);
		}else {
			productOptional = repository.findByIdAndStatus(id, status);
		}
		
		if (productOptional.isEmpty()) {
			throw new NoProductFoundException("Không tìm thấy sản phẩm.");
		}
		
		Product product = productOptional.get();
		
		if (status == null) {
			return Utils.generateObjectResponseEntity(mappingService.productToProductAdminResponseDTO(product), HttpStatus.OK);
		}else {
			return Utils.generateObjectResponseEntity(mappingService.productToProductResponseDTO(product), HttpStatus.OK);
		}
		
	}  
	
}
