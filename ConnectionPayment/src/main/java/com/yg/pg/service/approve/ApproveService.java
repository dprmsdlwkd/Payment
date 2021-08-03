package com.yg.pg.service.approve;

import org.springframework.stereotype.Service;

import com.yg.pg.controller.approve.dto.ApproveRequest;
import com.yg.pg.controller.approve.dto.ApproveResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApproveService {
	
	private final KakaopayApproveProcessService kakaopayApproveProcessService;
//	private final NaverpayApproveProcessService naverpayCreateProcessService;
	
	public ApproveResponse approve(String pgId, ApproveRequest approveRequest) {
		
		if (pgId.equals("kakao-pay")) {
			return kakaopayApproveProcessService.kakaopayApprove(approveRequest);
		}
		else if (pgId.equals("naver-pay")) {
//			return naverpayCreateProcesService.naverpayCreate(createRequest);
			return null;
		}
		else {
			ApproveResponse approveResponse = new ApproveResponse();
			approveResponse.setOrderNo("결제 생성 오류");
			return  approveResponse;
		}
	}
}
