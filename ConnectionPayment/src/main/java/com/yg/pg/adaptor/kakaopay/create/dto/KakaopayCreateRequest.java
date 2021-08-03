package com.yg.pg.adaptor.kakaopay.create.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayCreateRequest {

	private String orderNo;
	private String cid;
	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private int quantity;
	private int total_amount;
	private int tax_amount;
	private int tax_free_amount;
	private int vat_amount;
	private String approval_url;
	private String cancel_url;
	private String fail_url;
}
