package com.yg.pg.service.cancel;

import org.springframework.stereotype.Service;

import com.yg.pg.entity.Payment;
import com.yg.pg.repository.PaymentRepository;
import com.yg.pg.service.create.CreateTransactionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CancelTransactionService {
	private final PaymentRepository paymentRepository;
	
	public void paymentUpdate(Payment findPayment) {
		findPayment.setCancelYn("Y");
		paymentRepository.save(findPayment);
	}

}
