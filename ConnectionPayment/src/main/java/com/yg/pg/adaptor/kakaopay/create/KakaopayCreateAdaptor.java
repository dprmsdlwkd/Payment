package com.yg.pg.adaptor.kakaopay.create;

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
import com.yg.pg.adaptor.kakaopay.create.dto.CreateHeader;
import com.yg.pg.adaptor.kakaopay.create.dto.KakaopayCreateRequest;
import com.yg.pg.adaptor.kakaopay.create.dto.KakaopayCreateResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaopayCreateAdaptor {
	private String adminkey = "4f006d72a742740fb3dc893c3b93c1ad";
	private String kpayServerUrl = "https://kapi.kakao.com";
 
	ObjectMapper objectMapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();
	
	public KakaopayCreateResponse kakaopayCreate(KakaopayCreateRequest kakaopayCreateRequest) {
		
		kakaopayCreateRequest.setCid("TC0ONETIME");
		log.info(kakaopayCreateRequest.toString());
		
		// 통신 구현
		KakaopayCreateResponse response = new KakaopayCreateResponse();
		
		try {
			CreateHeader createHeader = CreateHeader.builder().authorization("KakaoAK " + adminkey).build();
			
			HttpHeaders header = new HttpHeaders();
			Map<String, String> headerParamMap;
			MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<>();
			
			headerParamMap = BeanUtils.describe(createHeader);
			header.setAll(headerParamMap);
			
			Map<String, String> map = objectMapper.convertValue(kakaopayCreateRequest, new TypeReference<Map<String, String>>(){
			});
			bodyParamMap.setAll(map);
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(bodyParamMap, header);
			
			URI uri = new URI(kpayServerUrl+"/v1/payment/ready");
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, KakaopayCreateResponse.class).getBody();
			response.setReturnCode(0); // return code
		}
		catch (HttpStatusCodeException e) {
			log.error("[KakaopayCreateAdaptor.paymentCreateStep1][orderNo: {}] HttpStatusCodeException : {}",kakaopayCreateRequest.getOrderNo(), e.getResponseBodyAsString());
		}catch (Exception e) {
			log.error("[KakaopayCreateAdaptor.paymentCreateStep1][orderNo: {}] HttpStatusCodeException : {}",kakaopayCreateRequest.getOrderNo(), e.toString());
		}finally {
			log.info("Result:" + response.toString());
		    return response;
		}
	}

}
