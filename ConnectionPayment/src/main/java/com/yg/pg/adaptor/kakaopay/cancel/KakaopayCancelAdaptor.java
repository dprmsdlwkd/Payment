package com.yg.pg.adaptor.kakaopay.cancel;

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
import com.yg.pg.adaptor.kakaopay.cancel.dto.request.KakaopayCancelRequest;
import com.yg.pg.adaptor.kakaopay.cancel.dto.response.KakaopayCancelResponse;
import com.yg.pg.adaptor.kakaopay.create.dto.CreateHeader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaopayCancelAdaptor {

	private String adminkey = "4f006d72a742740fb3dc893c3b93c1ad";
	private String kpayServerUrl = "https://kapi.kakao.com";
	
	ObjectMapper objectMapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();
	
	public KakaopayCancelResponse kakaopayCancel(KakaopayCancelRequest kakaopayCancelRequest) {

		kakaopayCancelRequest.setCid("TC0ONETIME"); // cid set

		// 통신 구현
		KakaopayCancelResponse response = new KakaopayCancelResponse();

		try {
			CreateHeader createHeader = CreateHeader.builder().authorization("KakaoAK " + adminkey).build();

			HttpHeaders header = new HttpHeaders();
			Map<String, String> headerParamMap;
			MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<>();

			headerParamMap = BeanUtils.describe(createHeader);
			header.setAll(headerParamMap);

			Map<String, String> map = objectMapper.convertValue(kakaopayCancelRequest, new TypeReference<Map<String, String>>() {});
			bodyParamMap.setAll(map);

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(
					bodyParamMap, header);
			

			URI uri = new URI(kpayServerUrl + "/v1/payment/cancel");
			
			System.out.println("headerParamMap => " + headerParamMap);
			System.out.println("bodyParamMap => " + bodyParamMap);
			System.out.println("cancel uri => " + uri.toString());
			
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, KakaopayCancelResponse.class).getBody();
			
		}
		catch(HttpStatusCodeException e) {
			log.error("[ApproveAdaptor.paymentApproveStep1][orderNo: {}] HttpStatusCodeException : {}",kakaopayCancelRequest.getOrderNo(),e.toString());
		}
		catch (Exception e) {
			log.error("[ApproveAdaptor.paymentApproveStep1][orderNo: {}] Exception : {}",kakaopayCancelRequest.getOrderNo(),e.toString());
		}
		finally {
			log.info("Result:" + response.toString());
		     return response;
		}
	}
}
