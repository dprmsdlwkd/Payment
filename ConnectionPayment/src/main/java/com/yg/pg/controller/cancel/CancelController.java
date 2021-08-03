package com.yg.pg.controller.cancel;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pg.controller.approve.ApproveController;
import com.yg.pg.controller.approve.dto.ApproveRequest;
import com.yg.pg.controller.approve.dto.ApproveResponse;
import com.yg.pg.controller.cancel.dto.CancelRequest;
import com.yg.pg.controller.cancel.dto.CancelResponse;
import com.yg.pg.service.approve.ApproveService;
import com.yg.pg.service.cancel.CancelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payment")
public class CancelController {
	
	private final CancelService cancelService;
	
	@PostMapping(value = "/{pg_id}/cancel") 
	public CancelResponse cancel(@PathVariable(value = "pg_id") String pgId, @RequestBody @Valid CancelRequest cancelRequest) {
	
	log.info("pgId => " + pgId);
	System.out.println("CancelRequest => " + cancelRequest.toString());
	
	return cancelService.cancel(pgId, cancelRequest);
	
}

}
