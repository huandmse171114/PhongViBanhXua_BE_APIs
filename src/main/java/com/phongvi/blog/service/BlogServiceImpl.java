package com.phongvi.blog.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phongvi.blog.Blog;
import com.phongvi.blog.BlogRepository;
import com.phongvi.blog.BlogStatus;
import com.phongvi.blog_category.BlogCategory;
import com.phongvi.blog_category.BlogCategoryRepository;
import com.phongvi.exception.NoBlogCategoryFoundException;
import com.phongvi.exception.NoBlogFoundException;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
	private final BlogRepository repository;
	private final BlogMappingService mappingService;
	private final UserRepository userRepository;
	private final BlogCategoryRepository blogCategoryRepository;
	private final int DEFAULT_PAGE = 0;
	private final int DEFAULT_SIZE = 150;
	
	@Override
	public ResponseEntity<?> getAllBlogByStatus(BlogStatus status, Integer page, Integer size, String title,
			String fromDateStr, String toDateStr, String author, Long categoryId, boolean isAdminCalled) {
		
//		LocalDate fromDate;
//		LocalDate toDate;
//		
//		if (fromDateStr.isEmpty()) {
//			LocalDate todayDate = LocalDate.now();
////			Neu nguoi dung khong nhap ngay cu the, lay ngay dau tien trong thang
//			fromDate = todayDate.withDayOfMonth(1);
//			System.out.println(fromDate.toString());
//		}else {
//			fromDate = LocalDate.parse(fromDateStr);			
//			System.out.println(fromDate.toString());
//		}
//		
//		
//		if (toDateStr.isEmpty()) {
//			toDate = LocalDate.now();
//			System.out.println(toDate.toString());
//		}else {
//			toDate = LocalDate.parse(toDateStr);
//			System.out.println(toDate.toString());
//		}
//		
//		Optional<BlogCategory> categoryOptional = blogCategoryRepository.findById(categoryId);
//		
//		if (categoryOptional.isEmpty()) {
//			throw new NoBlogCategoryFoundException("Không tìm thấy blog tương ứng: danh mục blog không tồn tại.");
//		}
//		
//		BlogCategory category = categoryOptional.get();
//		
//		Optional<User> userOptional = userRepository.findByUsername(author.toLowerCase());
//		
//		if (userOptional.isEmpty()) {
//			throw new NoBlogCategoryFoundException("Không tìm thấy blog tương ứng: tác giả không tồn tại.");
//		}
//		
//		if (page == null) page = DEFAULT_PAGE;
//		if (size == null) size = DEFAULT_SIZE;
//		
//		Pageable paging = PageRequest.of(page, size);
//		
//		Page<Blog> pageBlogs;
//		
//		if (status == null) {
////			Get all blogs with ANY status
//			pageBlogs = repository.findAllByTitleContainingIgnoreCaseAndCreatedByAndCategoryAndCreatedAtBetweenOrderByCreatedAtDesc(title, author, category, fromDate, toDate, paging);
//		}else {
////			Get all blogs with SPECIFIC status
//			pageBlogs = repository.findAllByTitleContainingIgnoreCaseAndCreatedByAndCategoryAndStatusCreatedAtBetweenOrderByCreatedAtDesc(title, author, category, status, toDate, toDate, paging);
//		}
//		
//		List<Blog> blogs = pageBlogs.getContent();
//		
//		if (blogs.isEmpty()) {
//			throw new NoBlogFoundException("Không tìm thấy blog tương ứng.");
//		}
//		
//		List responsee;
//		
		
		return null;
	}
	
	
}
