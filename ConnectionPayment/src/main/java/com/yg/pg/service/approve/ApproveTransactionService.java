package com.yg.pg.service.approve;

import org.springframework.stereotype.Service;

import com.yg.pg.adaptor.kakaopay.approve.dto.KakaopayApproveResponse;
import com.yg.pg.entity.Payment;
import com.yg.pg.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ApproveTransactionService {
	
	private final PaymentRepository paymentRepository;
	

	public void paymentUpdate(Payment findPayment, KakaopayApproveResponse kakaopayApproveResponse) {
		findPayment.setApproveYn("Y");
		findPayment.setPgTransactionId(kakaopayApproveResponse.getTid());
		paymentRepository.save(findPayment);
	}
}
