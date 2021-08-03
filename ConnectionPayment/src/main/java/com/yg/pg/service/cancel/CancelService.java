package com.yg.pg.service.cancel;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.yg.pg.controller.cancel.dto.CancelRequest;
import com.yg.pg.controller.cancel.dto.CancelResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancelService {
	private final KakaopayCancelProcessService kakaopayCancelProcessService;
	
	public CancelResponse cancel(String pgId, @Valid CancelRequest cancelRequest) {
		
		if (pgId.equals("kakao-pay")) {
			return kakaopayCancelProcessService.kakaopayCancel(cancelRequest);
		}
		else {
			CancelResponse cancelResponse = new CancelResponse();
			cancelResponse.setOrderNo("결제 생성 오류");
			return  cancelResponse;
		}
		
	}
}
