package com.yg.pg.service.cancel;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.yg.pg.adaptor.kakaopay.cancel.KakaopayCancelAdaptor;
import com.yg.pg.adaptor.kakaopay.cancel.dto.request.KakaopayCancelRequest;
import com.yg.pg.adaptor.kakaopay.cancel.dto.response.KakaopayCancelResponse;
import com.yg.pg.controller.cancel.dto.CancelRequest;
import com.yg.pg.controller.cancel.dto.CancelResponse;
import com.yg.pg.entity.Payment;
import com.yg.pg.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaopayCancelProcessService {
	
	private final KakaopayCancelAdaptor cancelAdaptor;
	private final CancelTransactionService cancelTransactionService; 
	private final PaymentRepository paymentRepository;

	public CancelResponse kakaopayCancel(@Valid CancelRequest cancelRequest) {

		Payment findPayment = paymentRepository.findByOrderNo(cancelRequest.getOrderNo());
		
		// 카카오페이 통신용 Request 생성
		KakaopayCancelResponse kakaopayCancelResponse = kakaopayCancelStep1(findPayment);
		
		if(kakaopayCancelResponse.getReturnCode() == 0) {
			log.info("KakaopayCancelResponse => " + kakaopayCancelResponse);
			
			// payment cancelYn update
			cancelTransactionService.paymentUpdate(findPayment);
			
			return CancelResponse.builder()
					.msg("Success")
					.orderNo(cancelRequest.getOrderNo())
					.cancelTotalAmount(kakaopayCancelResponse.getCanceled_amount().getTotal())
					.build()
					;
		}
		else {
			return CancelResponse.builder()
					.msg("Fail")
					.orderNo("결제 승인 오류")
					.build()
					;
		}
	}
	
		public KakaopayCancelResponse kakaopayCancelStep1(Payment findPayment) {
			
			KakaopayCancelRequest kakaopayCancelRequest = KakaopayCancelRequest.builder()
					  .tid(findPayment.getPgTransactionId()) 												// pg tid
					  .cancel_amount(findPayment.getAmount()) 											// 취소 금액
					  .cancel_tax_free_amount(findPayment.getTaxFreeSupplyAmount()) 						// 비과세 공급가 
					  .cancel_vat_amount(findPayment.getVatAmount()) 										// 부가세 금액
//					  .paymentId(cancelRequest.getPaymentId())
//					  .orderNo(cancelRequest.getOrderNo())
					  .build();
	return cancelAdaptor.kakaopayCancel(kakaopayCancelRequest);
		}
}
