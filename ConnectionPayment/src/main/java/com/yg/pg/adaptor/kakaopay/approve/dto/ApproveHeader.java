package com.yg.pg.adaptor.kakaopay.approve.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApproveHeader {
	private String authorization;
}
