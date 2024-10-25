package com.phongvi.utils;

import java.sql.Timestamp;

import lombok.Builder;

@Builder
public record ObjectResponseDTO(
			Object data,
			Timestamp timestamp,
			int status
		) {
}
