package com.yg.pg.controller.approve.dto;

import com.yg.pg.controller.create.dto.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApproveRequest {
	private String orderNo;
	private String memberNo;
	private String tid;
	private String pgToken;
	

}
