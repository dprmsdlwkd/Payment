package com.yg.pg.controller.cancel.dto;

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
public class CancelResponse {
	
	private String msg;
	private String orderNo;
	private int cancelTotalAmount;
	
}
