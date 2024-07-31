package com.phongvi.product_image.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongvi.product_image.service.ProductImageService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product Image", description = "Product Image Management APIs")
@RequestMapping("/store/api/v1/product-images")
public class ProductImageController {
	private final ProductImageService service;
}
