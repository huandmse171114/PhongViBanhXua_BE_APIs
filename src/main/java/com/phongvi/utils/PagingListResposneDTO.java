package com.phongvi.utils;

import java.util.List;

import lombok.Builder;

@Builder
public record PagingListResposneDTO(
			long totalItems,
			List items,
			int totalPages,
			int currentPage
		) {

}
