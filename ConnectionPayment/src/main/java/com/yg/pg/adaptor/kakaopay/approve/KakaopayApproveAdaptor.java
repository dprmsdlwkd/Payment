package com.yg.pg.adaptor.kakaopay.approve;

import java.net.URI;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg.pg.adaptor.kakaopay.approve.dto.ApproveHeader;
import com.yg.pg.adaptor.kakaopay.approve.dto.KakaopayApproveRequest;
import com.yg.pg.adaptor.kakaopay.approve.dto.KakaopayApproveResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaopayApproveAdaptor {
	
	private String adminkey = "4f006d72a742740fb3dc893c3b93c1ad";
	private String kpayServerUrl = "https://kapi.kakao.com";

	public KakaopayApproveResponse kakaopayApprove(KakaopayApproveRequest kakaopayApproveRequest) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		KakaopayApproveResponse response = new KakaopayApproveResponse();

		kakaopayApproveRequest.setCid("TC0ONETIME");
		
		try {
			ApproveHeader approveHeader = ApproveHeader.builder().authorization("KakaoAK " + adminkey).build();

			HttpHeaders header = new HttpHeaders();
			Map<String, String> headerParamMap;
			MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<>();

			headerParamMap = BeanUtils.describe(approveHeader);
			header.setAll(headerParamMap);

			Map<String, String> map = objectMapper.convertValue(kakaopayApproveRequest, new TypeReference<Map<String, String>>() {});
			bodyParamMap.setAll(map);

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(bodyParamMap, header);
			
			URI uri = new URI(kpayServerUrl + "/v1/payment/approve");
			log.info("approve uri => " + uri);
			
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, KakaopayApproveResponse.class).getBody();
			log.info("ApproveAdaptor response => " + response);
			
			response.setReturnCode(0); // return code
			
		} catch (HttpStatusCodeException e) {
		    log.error("[KakaopayApproveAdaptor.paymentApproveStep1][orderNo: {}] HttpStatusCodeException : {}",kakaopayApproveRequest.getOrderNo(), e.getResponseBodyAsString());
		    
		}  catch (Exception e) {
			log.error("[KakaopayApproveAdaptor.paymentApproveStep1][orderNo: {}] Exception : {}",kakaopayApproveRequest.getOrderNo(), e.toString());
		} finally {
			log.info("Result:{}" + response.toString());
		     return response;
		}
	}
}
