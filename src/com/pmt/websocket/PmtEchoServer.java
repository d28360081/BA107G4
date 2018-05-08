package com.pmt.websocket;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.servlet.ServletException;
import javax.websocket.CloseReason;

@ServerEndpoint("/PmtEchoServer/{myName}")
public class PmtEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	//連線
	@OnOpen 
	public void onOpen(@PathParam("myName") String myName, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
	}

	//回傳訊息 推播
	@OnMessage
	public void onMessage(Session userSession, String message) {
	}
	//錯誤處理
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	//關閉
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
	



	//推撥  促銷只有單向
	public void broadcast(String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);			
//			PmtEchoServer pmtEchoServer = new PmtEchoServer();
//			pmtEchoServer.broadcast("上線啦");
		}
		System.out.println("Message received: " + message);		
	}
	

	
	
		
		
	
}
