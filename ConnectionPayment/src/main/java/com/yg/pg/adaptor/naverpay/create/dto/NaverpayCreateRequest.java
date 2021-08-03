package com.yg.pg.adaptor.naverpay.create.dto;

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
public class NaverpayCreateRequest {
	private String merchantPayKey;
	private String productName;
	private int totalPayAmount;
	private int taxScopeAmount;
	private int taxExScopeAmount;
	private String returnUrl;
	private String clientId;
	private String modelVersion;
	
	private String orderNo;
	

}
