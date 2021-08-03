package com.yg.pg.controller.approve;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pg.controller.approve.dto.ApproveRequest;
import com.yg.pg.controller.approve.dto.ApproveResponse;
import com.yg.pg.service.approve.ApproveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payment")
public class ApproveController {
	
	private final ApproveService approveService;
	
	@PostMapping(value = "/{pg_id}/approve") 
	public ApproveResponse approve(@PathVariable(value = "pg_id") String pgId, @RequestBody @Valid ApproveRequest approveRequest) {
	
	log.info("pgId => " + pgId);
	System.out.println("ApproveRequest => " + approveRequest.toString());
	
	return approveService.approve(pgId, approveRequest);
}
	
	
}
