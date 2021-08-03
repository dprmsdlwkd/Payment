package com.yg.pg.controller.create;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pg.controller.create.dto.CreateRequest;
import com.yg.pg.controller.create.dto.CreateResponse;
import com.yg.pg.service.create.CreateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payment")
public class CreateController {
	private final CreateService createService;
	
	@PostMapping(value = "/{pg_id}/create") 
		public CreateResponse create(@PathVariable(value = "pg_id") String pgId, @RequestBody @Valid CreateRequest createRequest) {
		
		log.info("pgId => " + pgId);
		log.info("CreateRequest => " + createRequest.toString());
		
		return createService.create(pgId, createRequest);
	}
	
	@GetMapping("/kakaopay_approve")
	public String approveOrder(@RequestParam("pg_token") String pgToken, Model model) {
		log.info("pg_token => " + pgToken);
		model.addAttribute("pg_token", pgToken);
		return pgToken;
	}
	
	@GetMapping("/naverpay_approve")
	public String naverpayApproveOrder(@RequestParam("resultCode") String resultCode, @RequestParam("paymentId") String paymentId, Model model) {
		log.info("[OrderController] naverpay resultCode => " + resultCode);
		log.info("[OrderController] naverpay paymentId => " + paymentId);
		model.addAttribute("payment_id", paymentId);
		return "order/npay_approve";
	}
}
