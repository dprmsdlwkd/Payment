package com.yg.pg.service.approve;

import org.springframework.stereotype.Service;

import com.yg.pg.adaptor.kakaopay.approve.KakaopayApproveAdaptor;
import com.yg.pg.adaptor.kakaopay.approve.dto.KakaopayApproveRequest;
import com.yg.pg.adaptor.kakaopay.approve.dto.KakaopayApproveResponse;
import com.yg.pg.controller.approve.dto.ApproveRequest;
import com.yg.pg.controller.approve.dto.ApproveResponse;
import com.yg.pg.entity.Payment;
import com.yg.pg.repository.PaymentRepository;
import com.yg.pg.service.create.CreateTransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaopayApproveProcessService {
	
	private final KakaopayApproveAdaptor approveAdaptor;
	private final ApproveTransactionService approveTransactionService; 
	private final PaymentRepository paymentRepository;
	
	public ApproveResponse kakaopayApprove(ApproveRequest approveRequest) {
		
		// 카카오페이 통신용 Request 생성
		KakaopayApproveResponse kakaopayApproveResponse = kakaopayApproveStep1(approveRequest);
		
		if(kakaopayApproveResponse.getReturnCode() == 0) {
			log.info("kakaopayCreateResponse => " + kakaopayApproveResponse);
			
			// payment approveYn update
			Payment findPayment = paymentRepository.findByOrderNo(approveRequest.getOrderNo());
			approveTransactionService.paymentUpdate(findPayment, kakaopayApproveResponse);
			
			return ApproveResponse.builder()
					.msg("Success")
					.orderNo(approveRequest.getOrderNo())
					.approveTotalAmount(kakaopayApproveResponse.getAmount().getTotal())
					.build()
					;
		}
		else {
			return ApproveResponse.builder()
					.msg("Fail")
					.orderNo("결제 승인 오류")
					.build()
					;
		}
	}
	
	public KakaopayApproveResponse kakaopayApproveStep1(ApproveRequest approveRequest) {
		
		KakaopayApproveRequest kakaopayApproveRequest = KakaopayApproveRequest.builder()
				  .tid(approveRequest.getTid()) // PG 거래번호
				  .partner_order_id(approveRequest.getOrderNo()) // 가맹점 주문번호
				  .partner_user_id(approveRequest.getMemberNo()) // 가맹점 회원 id
				   .pg_token(approveRequest.getPgToken())
				   .orderNo(approveRequest.getOrderNo())
				   .build();
		
		return approveAdaptor.kakaopayApprove(kakaopayApproveRequest);
	}
}
