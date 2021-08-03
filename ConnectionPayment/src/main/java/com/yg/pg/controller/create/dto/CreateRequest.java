package com.yg.pg.controller.create.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateRequest {

	private String orderNo;
	private String productName;
	private String memberNo;
	private int amount;
	private int taxSupplyAmount;
	private int taxFreeSupplyAmount;
	private int taxAmount;
	private int vatAmount;
	private int productCount;
	private String returnUrl;
	private String failUrl;
	private String cancelUrl;
}
