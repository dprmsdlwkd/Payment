package com.yg.pg.adaptor.kakaopay.create.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHeader {
	private String authorization;
}
