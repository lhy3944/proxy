package com.rackbong.proxy.vo;

import lombok.Data;

@Data
public class SlackMessage {
	private String text;
	private String channel;
	private String token;
}
