package com.yg.pg.adaptor.kakaopay.approve.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayApproveResponse {
	
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private Amount amount;
	private CardInfo cardInfo;
	private String itemName;
	private String itemCode;
	private int quantity;
	private Date createdAt;
	private String approvedAt;
	private String payload;
	
	private int returnCode;

}
