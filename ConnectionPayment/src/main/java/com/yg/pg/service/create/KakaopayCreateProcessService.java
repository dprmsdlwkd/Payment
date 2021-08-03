package com.yg.pg.service.create;

import org.springframework.stereotype.Service;

import com.yg.pg.adaptor.kakaopay.create.KakaopayCreateAdaptor;
import com.yg.pg.adaptor.kakaopay.create.dto.KakaopayCreateRequest;
import com.yg.pg.adaptor.kakaopay.create.dto.KakaopayCreateResponse;
import com.yg.pg.controller.create.dto.CreateRequest;
import com.yg.pg.controller.create.dto.CreateResponse;
import com.yg.pg.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaopayCreateProcessService {
	
	private final KakaopayCreateAdaptor createAdaptor; 
	private final CreateTransactionService createTransactionService; 
	
	public CreateResponse kakaopayCreate (CreateRequest createRequest) {
		
		// 카카오페이 통신용 Request 생성
		KakaopayCreateResponse kakaopayCreateResponse = kakaopayCreateStep1(createRequest);
		
		if(kakaopayCreateResponse.getReturnCode() == 0) { // 생성 성공
			log.info("kakaopayCreateResponse => " + kakaopayCreateResponse);
			
			// 생성 내역저장
			createTransactionService.paymentSave(createRequest);
			
			return CreateResponse.builder()
					.msg("Success")
					.orderNo(createRequest.getOrderNo())
					.token(kakaopayCreateResponse.getTid())
					.url(kakaopayCreateResponse.getNext_redirect_pc_url())
					.build()
					;
		}
		else { // 실패
			return CreateResponse.builder()
					.msg("Fail")
					.orderNo("결제 생성 오류")
					.token(null)
					.url(null)
					.build()
					;
		}
	}
	
	public KakaopayCreateResponse kakaopayCreateStep1(CreateRequest createRequest) {
		
		KakaopayCreateRequest createStep1Request = KakaopayCreateRequest.builder()
																  .partner_order_id(createRequest.getOrderNo()) // 가맹점 주문번호
																  .partner_user_id(createRequest.getMemberNo()) // 가맹점 회원 id
																  .item_name(createRequest.getProductName()) // 상품명
																  .quantity(createRequest.getProductCount()) // 상품 수
																  .total_amount(createRequest.getAmount()) // 상품 총액
																  .tax_free_amount(createRequest.getTaxFreeSupplyAmount()) // 상품 비과세 금액
																  .vat_amount(createRequest.getVatAmount()) // 상품 부가세 금액
																  .approval_url(createRequest.getReturnUrl()) 
																  .fail_url(createRequest.getFailUrl())
																  .cancel_url(createRequest.getCancelUrl())
																  //.orderNo(Long.toString(createRequest.getOrderNo())
																  .build();
		return createAdaptor.kakaopayCreate(createStep1Request);
	}
}
