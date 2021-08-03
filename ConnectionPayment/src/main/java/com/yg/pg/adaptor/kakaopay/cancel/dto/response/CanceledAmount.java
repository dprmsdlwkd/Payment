package com.yg.pg.adaptor.kakaopay.cancel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanceledAmount {

	private int total;
	private int tax_free;
	private int vat;
	private int point;
	private int discount;
}
