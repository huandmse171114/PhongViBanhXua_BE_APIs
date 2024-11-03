package com.phongvi.dataloader;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.phongvi.blog_category.BlogCategory;
import com.phongvi.blog_category.BlogCategoryRepository;
import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.cart_item.service.CartItemService;
import com.phongvi.product.dto.ProductCreateDTO;
import com.phongvi.product.dto.ProductImageProductCreateDTO;
import com.phongvi.product.service.ProductService;
import com.phongvi.product.service.ProductServiceImpl;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.shipment.service.ShipmentService;
import com.phongvi.shipment.service.ShipmentServiceImpl;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierRepository;
import com.phongvi.supplier.SupplierStatus;
import com.phongvi.user.service.UserService;
import com.phongvi.user.service.UserServiceImpl;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Component
@Profile(value = "dev")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	private final ProductCategoryRepository productCategoryRepository;
	private final SupplierRepository supplierRepository;
	private final UserService userService;
	private final ProductService productService;
	private final ShipmentService shipmentService;
	private final CartItemService cartItemService;
	private final BlogCategoryRepository blogCategoryRepository;
	private List<Long> productCategoriesId = new ArrayList<>();
	
	@Override
	public void run(String... args) throws Exception {
		
//		===================================================== Blog Category ====================================================
		
//		id =1
		blogCategoryRepository.save(BlogCategory.builder()
				.name("Câu chuyện Người Bán")
				.description("Chia sẻ câu chuyện về cuộc sống, hoàn cảnh và những khó khăn mà các cô chú bán hàng rong đang phải đối mặt. Mục tiêu là để khách hàng hiểu hơn về người bán và góp phần hỗ trợ cô chú.")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("admin")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("admin")
				.status(BlogCategoryStatus.ACTIVE)
				.build());
		
//		id =2
		blogCategoryRepository.save(BlogCategory.builder()
				.name("Quỹ Từ Thiện")
				.description("Cung cấp thông tin về số tiền đã quyên góp được, các hoạt động từ thiện đã thực hiện và kế hoạch sử dụng quỹ trong tương lai.")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("admin")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("admin")
				.status(BlogCategoryStatus.ACTIVE)
				.build());
		
//		id =3
		blogCategoryRepository.save(BlogCategory.builder()
				.name("Sản phẩm mới")
				.description("Giới thiệu về các món ăn mới ra mắt, là nơi để khách hàng có thể biết về các chương trình khuyến mãi và ưu đãi đặc biệt.")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("admin")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("admin")
				.status(BlogCategoryStatus.ACTIVE)
				.build());
		
//		id =4
		blogCategoryRepository.save(BlogCategory.builder()
				.name("Chia sẻ từ Khách Hàng")
				.description("Đăng tải các câu chuyện, đánh giá và phản hồi từ khách hàng. tạo cơ hội để khách hàng chia sẻ cảm nhận cũng như trải nghiệm của bản thân.")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("admin")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("admin")
				.status(BlogCategoryStatus.ACTIVE)
				.build());
		
		
		
//		===================================================== Parent-level Product Category =====================================
//		id = 1
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh mặn")
				.description("")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 2
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh ngọt")
				.description("")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 3
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh chiên và rán")
				.description("")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 4
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh hấp")
				.description("")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 5
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh nướng")
				.description("")
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
//		==================================================== Child-level Product Category ===============================================
		
//		id = 6
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh khọt")
				.description("Bánh nhỏ từ bột gạo, chiên giòn, thường có nhân tôm, ăn kèm với rau sống và nước mắm chua ngọt.")
				.parentCategory(productCategoryRepository.findById((long) 1).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 7
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh bèo")
				.description("Bánh hấp từ bột gạo, được đặt trong chén nhỏ, trên mặt có tôm chấy, mỡ hành, ăn kèm với nước mắm.")
				.parentCategory(productCategoryRepository.findById((long) 1).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 8
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh nậm")
				.description("Bánh từ bột gạo, gói lá chuối, nhân tôm thịt, hấp chín, ăn kèm với nước mắm chua ngọt.")
				.parentCategory(productCategoryRepository.findById((long) 1).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 9
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh ít trần")
				.description("Bánh từ bột nếp, nhân đậu xanh hoặc thịt, không có lớp vỏ ngoài, ăn kèm với nước mắm chua ngọt.")
				.parentCategory(productCategoryRepository.findById((long) 1).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 10
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh bò")
				.description("Bánh hấp từ bột gạo, men và đường, có vị ngọt và dai, thường có nhiều màu sắc khác nhau.")
				.parentCategory(productCategoryRepository.findById((long) 2).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 11
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh da lợn")
				.description("Bánh nhiều lớp từ bột nếp, bột năng, hấp chính, ăn kèm với nước cốt dừa và mè rang.")
				.parentCategory(productCategoryRepository.findById((long) 2).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 12
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh cam")
				.description("Bánh từ bột nếp, nhân đậu xanh, chiên giòn, có vị ngọt và thường được phủ một lớp đường bên ngoài.")
				.parentCategory(productCategoryRepository.findById((long) 2).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 13
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh tiêu")
				.description("Bánh từ bột mì, đường, chiên giòn, bên trong rỗng, thường được ăn vào buổi sáng.")
				.parentCategory(productCategoryRepository.findById((long) 3).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id  = 14
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh chuối chiên")
				.description("Bánh từ chuối, bột mì, chiên giòn, có vị ngọt và thơm.")
				.parentCategory(productCategoryRepository.findById((long) 3).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 15
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh bao")
				.description("Bánh từ bột mì, nhân thịt hoặc đậu xanh, hấp chín, có vỏ mềm mịn.")
				.parentCategory(productCategoryRepository.findById((long) 4).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 16
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh giò")
				.description("Bánh từ bột gạo, nhân thịt, mộc nhĩ, gói lá chuối, hấp chín, ăn kèm với nước mắm chua ngọt.")
				.parentCategory(productCategoryRepository.findById((long) 4).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		id = 17
		productCategoryRepository.save(ProductCategory.builder()
				.name("Bánh bông lan")
				.description("Bánh từ bột mì, trứng, đường, nướng chín, có vị ngọt, mềm mịn.")
				.parentCategory(productCategoryRepository.findById((long) 5).get())
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.status(ProductCategoryStatus.ACTIVE)
				.build());
		
//		============================================================ Supplier ============================================================
		productCategoriesId.clear();
		productCategoriesId.add((long) 6);
		productCategoriesId.add((long) 7);
		productCategoriesId.add((long) 8);
		
		supplierRepository.save(Supplier.builder()
				.name("Xe đẩy Cô Út Hiền")
				.description("Cô Út Hiền là một người phụ nữ lớn tuổi, đã bán bánh được 5 năm dọc đoạn đường Nguyễn Văn Cừ. Với sự chăm chỉ và kiên trì, cô chủ yếu bán các loại bánh truyền thống miền Nam như bánh chuối chiên, bánh ít, bánh bột lọc để nuôi sống gia đình và hai đứa cháu nhỏ đang đi học.")
				.ownerName("Cô Út Hiền")
				.phone("0977588901")
				.openedTime(Time.valueOf("12:00:00"))
				.closedTime(Time.valueOf("17:00:00"))
				.street("")
				.ward("An Lạc A")
				.district("Bình Tân")
				.province("Hồ Chí Minh")
				.wardCode("092837")
				.provinceId(192038)
				.districtId(3328190)
				.totalRating(0)
				.averageStar(0)
				.status(SupplierStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.categories(productCategoryRepository.findAllByIdIn(productCategoriesId))
				.build());
		
		productCategoriesId.clear();
		productCategoriesId.add((long) 9);
		productCategoriesId.add((long) 10);
		productCategoriesId.add((long) 11);
		
		supplierRepository.save(Supplier.builder()
				.name("Gánh hàng rong Bà Sáu")
				.description("Bà Sáu là một người phụ nữ góa chồng đã hơn 10 năm nay. Bà bán bánh ở chợ Bến Thành để nuôi ba đứa con ăn học. Với kinh nghiệm và tay nghề khéo léo, bà chuyên bán các loại bánh miền Trung như bánh bèo, bánh nậm, bánh ít trần.")
				.ownerName("Bà Sáu")
				.phone("0934587690")
				.openedTime(Time.valueOf("6:00:00"))
				.closedTime(Time.valueOf("11:00:00"))
				.street("Lê Lợi")
				.ward("Bến Thành")
				.district("Quận 1")
				.province("Hồ Chí Minh")
				.wardCode("092838")
				.provinceId(192038)
				.districtId(3328191)
				.totalRating(0)
				.averageStar(0)
				.status(SupplierStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.categories(productCategoryRepository.findAllByIdIn(productCategoriesId))
				.build());
		
		productCategoriesId.clear();
		productCategoriesId.add((long) 12);
		productCategoriesId.add((long) 3);
		productCategoriesId.add((long) 14);
		
		supplierRepository.save(Supplier.builder()
				.name("Quầy bánh Bà Tám")
				.description("Bà Tám là một người phụ nữ cần cù, sống ở khu vực phố cổ Hà Nội. Bà đã bán bánh truyền thống miền Bắc tại đây suốt 15 năm để nuôi chồng ốm yếu và hai con. Các loại bánh cuốn, bánh giò, bánh bột lọc của bà luôn được khách hàng ưa chuộng.")
				.ownerName("Bà Tám")
				.phone("0912345678")
				.openedTime(Time.valueOf("07:00:00"))
				.closedTime(Time.valueOf("14:00:00"))
				.street("Hàng Bạc")
				.ward("Hàng Bạc")
				.district("Hoàn Kiếm")
				.province("Hà Nội")
				.wardCode("092839")
				.provinceId(192039)
				.districtId(3328192)
				.totalRating(0)
				.averageStar(0)
				.status(SupplierStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.categories(productCategoryRepository.findAllByIdIn(productCategoriesId))
				.build());
		
		productCategoriesId.clear();
		productCategoriesId.add((long) 15);
		productCategoriesId.add((long) 16);
		productCategoriesId.add((long) 1);
		
		supplierRepository.save(Supplier.builder()
				.name("Xe bánh Bà Hạnh")
				.description("Bà Hạnh là một người phụ nữ thân thiện, đã bán bánh từ các loại bột khoai và sắn ở khu vực Đà Nẵng suốt 20 năm. Bà sống một mình và bán các loại bánh như bánh bột lọc, bánh bèo, bánh khoai mì để tự nuôi bản thân và hỗ trợ các trẻ em nghèo trong khu vực.")
				.ownerName("Bà Hạnh")
				.phone("0987654321")
				.openedTime(Time.valueOf("08:00:00"))
				.closedTime(Time.valueOf("15:00:00"))
				.street("Phan Châu Trinh")
				.ward("Hải Châu")
				.district("Hải Châu")
				.province("Đà Nẵng")
				.wardCode("092840")
				.provinceId(192040)
				.districtId(3328193)
				.totalRating(0)
				.averageStar(0)
				.status(SupplierStatus.ACTIVE)
				.createdAt(Utils.getCurrentTimestamp())
				.createdBy("HUANDM")
				.lastChangedAt(Utils.getCurrentTimestamp())
				.lastChangedBy("HUANDM")
				.categories(productCategoryRepository.findAllByIdIn(productCategoriesId))
				.build());
	
		// ==================================================================== Add product data ================================================================
		List<ProductImageProductCreateDTO> images = new ArrayList<>();
		List<Long> categoriesId = new ArrayList<>();
		
		// ============================================================ Bánh bò hấp ===================================================================================
		
		images.clear();
		images.add(ProductImageProductCreateDTO.builder()
				.index(0)
				.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_bo_hap.png?alt=media&token=1fad5bfe-6d34-4a24-9329-c51a786bcaee")
				.description("Ảnh bánh bò hấp")
				.build());
		
		categoriesId.clear();
		categoriesId.add(1L);
		categoriesId.add(2L);
		
		productService.saveProduct(ProductCreateDTO.builder()
				.name("Bánh Bò Hấp")
				.description("Bánh bò hấp truyền thống, thơm nhẹ mùi dừa, mềm xốp với vị ngọt thanh từ nước cốt dừa. Màu sắc tự nhiên từ lá dứa, lá cẩm, mang đến hương vị dân dã nhưng hấp dẫn. Thích hợp cho các dịp lễ Tết hay những buổi họp mặt gia đình.")
				.price(19000L)
				.discountPrice(15000L)
				.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
				.calories(82)
				.dailyStock(20)
				.images(images)
				.categories(categoriesId)
				.supplier(1L)
				.build());
		

		// ============================================================ Bánh Bò Nướng ===================================================================================
		
		images.clear();
		images.add(ProductImageProductCreateDTO.builder()
				.index(0)
				.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_bo_nuong.png?alt=media&token=8fe5323a-e448-4a03-9772-276b9cd6400e")
				.description("Ảnh bánh bò nướng")
				.build());
		
		categoriesId.clear();
		categoriesId.add(3L);
		categoriesId.add(5L);
		
		productService.saveProduct(ProductCreateDTO.builder()
				.name("Bánh Bò Nướng")
				.description("Bánh bò nướng với lớp vỏ nâu bóng giòn, bên trong mềm, dai và có mùi thơm đặc trưng của nước cốt dừa. Thớ bánh rỗ xốp độc đáo tạo cảm giác thú vị khi thưởng thức.")
				.price(25000L)
				.discountPrice(21000L)
				.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
				.calories(115)
				.dailyStock(20)
				.images(images)
				.categories(categoriesId)
				.supplier(2L)
				.build());
		
		// ============================================================ Banh Cam ===================================================================================
		
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_cam.png?alt=media&token=8dfe57be-2e7e-4db7-b850-5001f1c73efd")
						.description("Ảnh bánh cam")
						.build());
				
				categoriesId.clear();
				categoriesId.add(1L);
				categoriesId.add(3L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Cam")
						.description("Bánh cam giòn rụm, vỏ ngoài vàng nâu giòn tan với nhân đậu xanh ngọt mịn, là món ăn vặt dân dã đặc trưng.")
						.price(25000L)
						.discountPrice(21000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(115)
						.dailyStock(40)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
				// ============================================================ Bánh Chuối Nướng ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_chuoi_nuong.png?alt=media&token=1b723689-3643-4116-b354-30a71af961f9")
						.description("Ảnh bánh chuối nướng")
						.build());
				
				categoriesId.clear();
				categoriesId.add(4L);
				categoriesId.add(3L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Chuối Nướng")
						.description("Bánh chuối nướng có vị ngọt tự nhiên từ chuối, thơm mùi nước cốt dừa, và lớp vỏ nâu giòn bên ngoài. Thích hợp cho các dịp lễ hội và những ai yêu thích món ngọt truyền thống.")
						.price(18000L)
						.discountPrice(15000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(300)
						.dailyStock(10)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Da Lợn ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_da_lon.png?alt=media&token=94f9216e-83c8-4567-82b9-80f4f6448bf8")
						.description("Ảnh bánh da lợn")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Da Lợn")
						.description("Bánh da lợn được làm từ bột năng và đậu xanh, từng lớp bánh mỏng dẻo, thơm ngọt, thường có màu xanh từ lá dứa và trắng từ nước cốt dừa.")
						.price(18000L)
						.discountPrice(16000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(100)
						.dailyStock(50)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Đậu Xanh Hình Trái Cây ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_dau_xanh_hinh_trai_cay.png?alt=media&token=c2e4af2f-5204-433e-9d42-4eb6551f7a88")
						.description("Ảnh bánh đậu xanh hình trái cây")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Đậu Xanh Hình Trái Cây")
						.description("Bánh đậu xanh tạo hình trái cây nhiều màu sắc, nhân đậu xanh bùi ngọt, mang lại cảm giác vừa ngon miệng vừa vui mắt.")
						.price(5000L)
						.discountPrice(3000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Đúc Gân ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_duc_gan.png?alt=media&token=ad663936-9aa3-4b1a-b81f-e69506861b0d")
						.description("Ảnh bánh đúc gân")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Đúc Gân")
						.description("Bánh đúc gân có lớp bánh mềm mịn, với từng tầng màu sắc xen kẽ từ lá dứa và đậu xanh. Hương vị ngọt bùi, thơm phức cùng chút béo ngậy từ nước cốt dừa.")
						.price(19000L)
						.discountPrice(17000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Đúc Gân Tam Sắc ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_duc_gan.png?alt=media&token=ad663936-9aa3-4b1a-b81f-e69506861b0d")
						.description("Ảnh bánh đúc gân tam sắc")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Đúc Gân Tam Sắc")
						.description("Bánh đúc gân tam sắc với ba màu tự nhiên xanh, vàng, và trắng xen kẽ tạo thành lớp vân độc đáo. Bánh mềm mịn, thơm mùi nước cốt dừa và lá dứa, đậu xanh. Hương vị ngọt thanh, vừa mềm vừa dai, là lựa chọn lý tưởng cho các dịp lễ hoặc thưởng thức hàng ngày.")
						.price(20000L)
						.discountPrice(18000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Bò Xốp Lá Dứa ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_duc_gan.png?alt=media&token=ad663936-9aa3-4b1a-b81f-e69506861b0d")
						.description("Ảnh bánh bò xốp lá dứa")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Bò Xốp Lá Dứa")
						.description("Bánh bò xốp lá dứa mềm mịn với màu xanh mướt từ lá dứa tự nhiên, mang hương thơm dịu nhẹ và vị ngọt thanh từ nước cốt dừa. Bánh có kết cấu xốp rễ tre đặc trưng, tan chảy trong miệng với mỗi miếng cắn. Thích hợp cho các buổi họp mặt hoặc làm món tráng miệng dân dã.")
						.price(20000L)
						.discountPrice(18000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Lá Mơ ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_la_mo.png?alt=media&token=e90fe6ef-8d15-4452-a71a-ca4d198d4101")
						.description("Ảnh bánh lá mơ")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Lá Mơ")
						.description("Bánh lá mơ được gói trong lá chuối, có hương vị thơm ngậy của nước cốt dừa và gạo nếp, ăn kèm với muối vừng tạo nên vị mặn ngọt hài hòa.")
						.price(20000L)
						.discountPrice(18000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Đúc Lạc ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_la_mo.png?alt=media&token=e90fe6ef-8d15-4452-a71a-ca4d198d4101")
						.description("Ảnh bánh đúc lạc")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Đúc Lạc")
						.description("Bánh đúc lạc là món ăn dân dã với lớp bánh trắng mịn từ bột gạo, điểm thêm những hạt lạc bùi béo thơm ngon. Bánh mềm, dẻo vừa phải, có vị ngọt nhẹ, thường ăn kèm với muối vừng hoặc nước mắm pha tạo nên hương vị hài hòa và hấp dẫn.")
						.price(20000L)
						.discountPrice(18000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Ú ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_u.png?alt=media&token=5edf6e6a-b5e7-4770-a7d5-268ce88cc290")
						.description("Ảnh bánh ú")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Ú")
						.description("Bánh ú nhân đậu xanh, nếp dẻo thơm, được gói lá chuối vuông vắn, thường xuất hiện trong các dịp lễ cúng.")
						.price(19000L)
						.discountPrice(17000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh In ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_u.png?alt=media&token=5edf6e6a-b5e7-4770-a7d5-268ce88cc290")
						.description("Ảnh bánh in")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh In")
						.description("Bánh in là món bánh truyền thống với lớp bột trắng mịn, mềm tan trong miệng, thường có hương vị ngọt dịu và thơm mùi hoa bưởi hoặc dừa. Bánh được in khuôn với nhiều hoa văn đẹp mắt, là món bánh không thể thiếu trong các dịp lễ Tết hay cúng gia tiên.")
						.price(21000L)
						.discountPrice(19000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
// ============================================================ Bánh Tét Chuối ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_tet_chuoi.png?alt=media&token=c56670c0-1005-42bc-a0d2-3c65346fc582")
						.description("Ảnh bánh tét chuối")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Tét Chuối")
						.description("Bánh tét chuối với hương vị ngọt nhẹ, nhân chuối đỏ và lớp nếp dẻo thơm, là món bánh truyền thống vào dịp Tết.")
						.price(21000L)
						.discountPrice(19000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());

// ============================================================ Bánh Tét Chuối ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_tet_chuoi.png?alt=media&token=c56670c0-1005-42bc-a0d2-3c65346fc582")
						.description("Ảnh bánh chuối hấp thốt nốt")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Chuối Hấp Thốt Nốt")
						.description("Bánh chuối hấp thốt nốt với màu vàng nâu tự nhiên từ đường thốt nốt, thơm lừng hương chuối chín và nước cốt dừa béo ngậy. Bánh mềm mịn, ngọt dịu và có kết cấu dẻo dính đặc trưng, thường được phủ lớp nước cốt dừa mặn ngọt hài hòa, thích hợp làm món tráng miệng dân dã.")
						.price(19000L)
						.discountPrice(17000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
				
				
// ============================================================ Bánh Khoai Mì Nướng Dừa Non ===================================================================================
				
				images.clear();
				images.add(ProductImageProductCreateDTO.builder()
						.index(0)
						.source("https://firebasestorage.googleapis.com/v0/b/phongvibanhxua.appspot.com/o/cakes%2FPVBX__banh_tet_chuoi.png?alt=media&token=c56670c0-1005-42bc-a0d2-3c65346fc582")
						.description("Ảnh bánh khoai mì nướng dừa non")
						.build());
				
				categoriesId.clear();
				categoriesId.add(2L);
				categoriesId.add(6L);
				
				productService.saveProduct(ProductCreateDTO.builder()
						.name("Bánh Khoai Mì Nướng Dừa Non")
						.description("Bánh khoai mì nướng dừa non thơm ngọt tự nhiên với lớp vỏ giòn và bên trong mềm dẻo từ khoai mì bào. Thêm dừa non bùi béo, bánh mang hương vị đậm đà và mùi thơm ngậy của nước cốt dừa. Phù hợp cho các bữa ăn nhẹ hoặc món tráng miệng dân dã.")
						.price(20000L)
						.discountPrice(18000L)
						.discountExpiry(Utils.getNextDay(java.sql.Date.valueOf(LocalDate.now())))
						.calories(20)
						.dailyStock(30)
						.images(images)
						.categories(categoriesId)
						.supplier(3L)
						.build());
	}
	
	
	
}
