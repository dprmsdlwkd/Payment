package com.yg.pg.service.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.pg.controller.create.dto.CreateRequest;
import com.yg.pg.entity.Payment;
import com.yg.pg.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CreateTransactionService {
	
	private final PaymentRepository paymentRepository;
	
	public void paymentSave(CreateRequest createRequest) {
		Payment payment = Payment.builder()
				.orderNo(createRequest.getOrderNo())
				.productName(createRequest.getProductName())
				.memberNo(createRequest.getMemberNo())
				.amount(createRequest.getAmount())
				.taxSupplyAmount(createRequest.getTaxSupplyAmount())
				.taxFreeSupplyAmount(createRequest.getTaxFreeSupplyAmount())
				.taxAmount(createRequest.getTaxAmount())
				.vatAmount(createRequest.getVatAmount())
				.productCount(createRequest.getProductCount())
				.build();
		
		paymentRepository.save(payment);
	}

}
