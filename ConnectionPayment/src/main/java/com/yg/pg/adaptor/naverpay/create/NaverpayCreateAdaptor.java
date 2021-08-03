package com.yg.pg.adaptor.naverpay.create;

import java.net.URI;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg.pg.adaptor.naverpay.create.dto.NaverpayCreateRequest;
import com.yg.pg.adaptor.naverpay.create.dto.NaverpayCreateResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NaverpayCreateAdaptor {
	
	private String npayClientKey = "testKey";
	private String npayModelVersion = "2";
	private String npayServerUrl = "https://dev.apis.naver.com";
	//private String NpayPartnerId = "naverpay-partner";
	private String NpayPartnerId = "naverpay-partner";
	
	ObjectMapper objectMapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();
	
	public NaverpayCreateResponse naverpayCreate(NaverpayCreateRequest naverpayCreateRequest) {
		
		naverpayCreateRequest.setClientId(npayClientKey);
		naverpayCreateRequest.setModelVersion(npayModelVersion);
		
		// 통신 구현
		NaverpayCreateResponse response = new NaverpayCreateResponse();
		
		try {
			// header
			HttpHeaders headers = new HttpHeaders(); 
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Naver-Client-Id", npayClientKey);
			headers.add("X-Naver-Client-Secret", npayModelVersion);
			
			// body
			String requestStr = objectMapper.writeValueAsString(naverpayCreateRequest);
			
			HttpEntity<String> entity = new HttpEntity<>(requestStr, headers);
	
			log.info("[CREATE] 5. Request...Entity => " + entity);
			
			// PG송신이력 저장 
			URI createUrI = new URI(npayServerUrl + "/"+NpayPartnerId +"/naverpay/payments/v2/reserve");
			log.info("[CREATE] 6. Request...Url => " + createUrI);
			
			response = restTemplate.exchange( createUrI, HttpMethod.POST, entity, NaverpayCreateResponse.class).getBody();
			
			log.info("[CREATE] 7. NpayCreateAdaptor(npayCreateStep1Response) => " + response);
			System.out.println("reserve => " + response.getBody().getReserveId());
		}
		catch (HttpStatusCodeException e) {
			log.error("[NaverpayCreateAdaptor.naverpayCreateStep1][orderNo: {}] HttpStatusCodeException : {}",naverpayCreateRequest.getOrderNo(), e.getResponseBodyAsString());
		}catch (Exception e) {
			log.error("[NaverpayCreateAdaptor.naverpayCreateStep1][orderNo: {}] Exception : {}",naverpayCreateRequest.getOrderNo(), e.toString());
		}finally {
			log.info("Result:" + response.toString());
		    return response;
		}
	}
}
