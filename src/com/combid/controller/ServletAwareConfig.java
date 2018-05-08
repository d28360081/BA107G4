package com.combid.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServletAwareConfig extends ServerEndpointConfig.Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession=(HttpSession)request.getHttpSession();
		if(httpSession!=null) {
			config.getUserProperties().put("httpSession", httpSession);
		}
		super.modifyHandshake(config, request, response);
	}
	

}
