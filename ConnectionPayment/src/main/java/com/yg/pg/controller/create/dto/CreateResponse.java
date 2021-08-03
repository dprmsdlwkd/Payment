package com.yg.pg.controller.create.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreateResponse {

	private String msg;
	private String orderNo;
	private String token;
	private String url;
}
