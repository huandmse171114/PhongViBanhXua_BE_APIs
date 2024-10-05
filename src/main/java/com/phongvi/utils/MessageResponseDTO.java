package com.phongvi.utils;


import java.sql.Timestamp;

import lombok.Builder;

@Builder
public record MessageResponseDTO(
			String message,
			Timestamp timestamp,
			int status
		) {

}
