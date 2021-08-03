package com.yg.pg.adaptor.naverpay.create.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaverpayCreateResponse {
	private String code;
	private String message;
	private NaverpayCreateBody body;
}
