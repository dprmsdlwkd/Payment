package com.yg.pg.service.create;

import org.springframework.stereotype.Service;

import com.yg.pg.adaptor.naverpay.create.NaverpayCreateAdaptor;
import com.yg.pg.adaptor.naverpay.create.dto.NaverpayCreateRequest;
import com.yg.pg.adaptor.naverpay.create.dto.NaverpayCreateResponse;
import com.yg.pg.controller.create.dto.CreateRequest;
import com.yg.pg.controller.create.dto.CreateResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverpayCreateProcessService {
	private final NaverpayCreateAdaptor createAdaptor; 
	
	public CreateResponse naverpayCreate(CreateRequest createRequest) {
		
		CreateResponse createResponse = new CreateResponse();
		
		// 네이버페이 통신용 Request 생성
		NaverpayCreateResponse naverpayCreateResponse = naverpayCreateStep1(createRequest);
		
		if(naverpayCreateResponse.equals("Success")) {
			log.info("결제 성공");
			createResponse.setOrderNo(createRequest.getOrderNo());
			return createResponse;
		}
		else {
			createResponse.setOrderNo("naverpay 개발 중단 key인증 실패");
			return createResponse;
		}
	}
	
	public NaverpayCreateResponse naverpayCreateStep1(CreateRequest createRequest) {
		
		NaverpayCreateRequest naverpayCreateRequest = NaverpayCreateRequest.builder()
//				  .modelVersion(modelVersion) // naver modelversion
//				  .clientId(clientId) // 가맹점 회원 id
//				  .mode(mode) // 상품명
				  .merchantPayKey(createRequest.getOrderNo()) // 주문 번호
				  .productName(createRequest.getProductName()) // 상품명
				  .totalPayAmount(createRequest.getAmount()) // test pg -> server
				  .taxScopeAmount(createRequest.getTaxSupplyAmount() + createRequest.getVatAmount())
				  .taxExScopeAmount(createRequest.getTaxFreeSupplyAmount())
				  .returnUrl(createRequest.getReturnUrl())
//				  .paymentId(paymentId)
				  .orderNo(createRequest.getOrderNo())
				  .build();
		return createAdaptor.naverpayCreate(naverpayCreateRequest);
	}
}
