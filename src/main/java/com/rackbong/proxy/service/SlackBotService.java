package com.rackbong.proxy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rackbong.proxy.vo.SlackMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SlackBotService {

	@Value("${slackApiUrl}")
	private String slackApiUrl;

	public void postMessage(SlackMessage message) {
		try {
			log.info("SlackBotService.postMessage - baseURL : {}", slackApiUrl);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();

			// Header set
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setBearerAuth(message.getToken());

			// Body set
			Map<String, String> body = new HashMap<String, String>();
			body.put("text", message.getText());
			body.put("channel", message.getChannel());

			// Message
			HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

			log.info("SlackBotService.postMessage - httpRequest : {}", requestMessage);

			// Request
			restTemplate.postForEntity(slackApiUrl, requestMessage, String.class);

		} catch (Exception e) {
			log.error("SlackBotService.postMessage : {}", e.getMessage());
		}
	}
}
