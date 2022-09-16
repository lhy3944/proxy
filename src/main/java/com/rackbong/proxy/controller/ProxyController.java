package com.rackbong.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rackbong.proxy.service.SlackBotService;
import com.rackbong.proxy.vo.SlackMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/proxy/v1")
public class ProxyController {

	@Autowired
	SlackBotService slackBotService;

	@PostMapping("/slackPostMessage")
	public void slackPostMessage(@RequestBody SlackMessage message) {
		log.info("ProxyController.slackPostMessage: {}", message);
		slackBotService.postMessage(message);
	}
}
