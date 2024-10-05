package com.phongvi.dataloader;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.phongvi.blog_category.BlogCategory;
import com.phongvi.blog_category.BlogCategoryRepository;
import com.phongvi.blog_category.BlogCategoryStatus;
import com.phongvi.product_category.ProductCategory;
import com.phongvi.product_category.ProductCategoryRepository;
import com.phongvi.product_category.ProductCategoryStatus;
import com.phongvi.supplier.Supplier;
import com.phongvi.supplier.SupplierRepository;
import com.phongvi.supplier.SupplierStatus;
import com.phongvi.utils.Utils;

import lombok.RequiredArgsConstructor;

@Component
@Profile(value = "dev")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	private final ProductCategoryRepository productCategoryRepository;
	private final SupplierRepository supplierRepository;
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
	
	}
	
	
	
}
