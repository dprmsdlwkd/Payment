package com.yg.pg.adaptor.kakaopay.cancel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayCancelRequest {
	private String cid;
	private String tid;
	private int cancel_amount;
	private int cancel_tax_free_amount;
	private int cancel_vat_amount;
	
	// api-log 용
	private String paymentId;
	private long orderNo;
}
