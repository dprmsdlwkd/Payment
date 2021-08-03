package com.yg.pg.service.create;

import org.springframework.stereotype.Service;

import com.yg.pg.controller.create.dto.CreateRequest;
import com.yg.pg.controller.create.dto.CreateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateService {
	
	private final KakaopayCreateProcessService kakaopayCreateProcessService;
	private final NaverpayCreateProcessService naverpayCreateProcessService;

	public CreateResponse create(String pgId, CreateRequest createRequest) {
		
		if (pgId.equals("kakao-pay")) {
			return kakaopayCreateProcessService.kakaopayCreate(createRequest);
		}
		else if (pgId.equals("naver-pay")) {
			return naverpayCreateProcessService.naverpayCreate(createRequest);
		}
		else {
			CreateResponse createResponse = new CreateResponse();
			createResponse.setOrderNo("결제 생성 오류");
			return  createResponse;
		}
	}
}
