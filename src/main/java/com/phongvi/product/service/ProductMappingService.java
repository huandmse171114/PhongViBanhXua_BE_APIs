package com.phongvi.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.phongvi.cart_item.dto.CartItemProductResponse;
import com.phongvi.exception.NoProductCategoryFoundException;
import com.phongvi.exception.NoSupplierFoundException;
import com.phongvi.product.Product;
import com.phongvi.product.ProductStatus;
import com.phongvi.product.dto.ProductAdminResponseDTO;
import com.phongvi.product.dto.ProductComboItemAdminResponseDTO;
import com.phongvi.product.dto.ProductCreateDTO;
import com.phongvi.product.dto.ProductResponseDTO;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.service.ProductCategoryMappingService;
import com.phongvi.product_image.ProductImage;
import com.phongvi.product_image.ProductImageRepository;
import com.phongvi.product_image.service.ProductImageMappingService;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierRepository;
import com.phongvi.supplier.service.SupplierMappingService;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMappingService {
	
	private final ProductImageMappingService imageMappingService;
	private final SupplierMappingService supplierMappingService;
	private final SupplierRepository supplierRepository;
	private final ProductCategoryMappingService categoryMappingService;
	private final ProductCategoryRepository categoryRepository;
	
	public ProductAdminResponseDTO productToProductAdminResponseDTO(Product product) {
		return ProductAdminResponseDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.discountPrice(product.getDiscountPrice())
				.discountExpiry(product.getDiscountExpiry())
				.calories(product.getCalories())
				.totalRating(product.getTotalRating())
				.averageStar(product.getAverageStar())
				.dailyStock(product.getDailyStock())
				.status(product.getStatus())
				.createdAt(product.getCreatedAt())
				.createdBy(product.getCreatedBy())
				.lastChangedAt(product.getLastChangedAt())
				.lastChangedBy(product.getLastChangedBy())
				.images(product.getImages().stream()
						.map(image -> imageMappingService
								.productImageToProductImageProductResponseDTO(image))
								.toList())
				.categories(product.getCategories().stream()
						.map(category -> 
						categoryMappingService
								.productCategoryToCategoryProductResponseDTO(category))
						.toList())
				.supplier(supplierMappingService.supplierToSupplierProductResponseDTO(product.getSupplier()))
				.build();
	}
	
	public ProductResponseDTO productToProductResponseDTO(Product product) {
		return ProductResponseDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.discountPrice(product.getDiscountPrice())
				.discountExpiry(product.getDiscountExpiry())
				.calories(product.getCalories())
				.totalRating(product.getTotalRating())
				.averageStar(product.getAverageStar())
				.dailyStock(product.getDailyStock())
				.images(product.getImages().stream()
						.map(image -> imageMappingService
								.productImageToProductImageProductResponseDTO(image))
								.toList())
				.categories(product.getCategories().stream()
						.map(category -> 
						categoryMappingService
								.productCategoryToCategoryProductResponseDTO(category))
						.toList())
				.supplier(supplierMappingService.supplierToSupplierProductResponseDTO(product.getSupplier()))
				.build();
	}

	public Product productCreateDTOToProduct(ProductCreateDTO productDTO) {
		
		List<ProductImage> newProductImages = productDTO.images().stream()
				.map(image -> imageMappingService.productImageProductCreateDTOToProductImage(image))
				.toList();
		
		System.out.println(newProductImages);
		
		Optional<Supplier> supplierOptional = supplierRepository.findById(productDTO.supplier());
		
		if (supplierOptional.isEmpty()) {
			throw new NoSupplierFoundException("Tạo mới thất bại: không tìm thấy nhà cung cấp tương ứng.");
		}
		
		Supplier supplier = supplierOptional.get();
		
		List<ProductCategory> categories = categoryRepository.findAllByIdIn(productDTO.categories());
		
		if (categories.size() != productDTO.categories().size()) {
			throw new NoProductCategoryFoundException("Tạo mới thất bại: không tìm thấy một số danh mục sản phẩm tương ứng.");
		}
		
		return Product.builder()
				.name(productDTO.name())
				.description(productDTO.description())
				.price(productDTO.price())
				.discountPrice(productDTO.discountPrice())
				.discountExpiry(productDTO.discountExpiry())
				.calories(productDTO.calories())
				.dailyStock(productDTO.dailyStock())
				.status(ProductStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("")
				.images(newProductImages)
				.supplier(supplier)
				.categories(categories)
				.build();
	}

	public CartItemProductResponse productToCartItemProductResponse(Product product) {
		return CartItemProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.discountPrice(product.getDiscountPrice())
				.discountExpiry(product.getDiscountExpiry())
				.calories(product.getCalories())
				.firstImageUrl(product.getImages() != null && !product.getImages().isEmpty()
						? product.getImages().get(0).getSource() : null)
				.build();
	}
	
	public ProductComboItemAdminResponseDTO productToProductComboItemAdminResponseDTO(Product product) {
		return ProductComboItemAdminResponseDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.discountExpiry(product.getDiscountExpiry())
				.discountPrice(product.getDiscountPrice())
				.calories(product.getCalories())
				.totalRating(product.getTotalRating())
				.averageStar(product.getAverageStar())
				.images(product.getImages().stream()
						.map(image -> imageMappingService
								.productImageToProductImageProductResponseDTO(image))
								.toList())
				.categories(product.getCategories().stream()
						.map(category -> 
						categoryMappingService
								.productCategoryToCategoryProductResponseDTO(category))
						.toList())
				.supplier(supplierMappingService.supplierToSupplierProductResponseDTO(product.getSupplier()))
				.build();
	}
}
