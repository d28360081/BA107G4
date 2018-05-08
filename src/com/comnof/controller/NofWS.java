package com.comnof.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;

@ServerEndpoint("/NofWS/{uid}")
public class NofWS {
static ConcurrentHashMap<String,Session>  NofOnlineMap=new ConcurrentHashMap<String,Session>();
ComNofService cvs=new ComNofService();	
		
		@OnOpen
		public void onOpen(@PathParam("uid") String uid, Session userSession) throws IOException {
			try {
			//上限會員加入map	
			JSONObject jobMain=new JSONObject();
 			this.NofOnlineMap.put(uid, userSession);
 			List<ComNofVO> Historylist=cvs.selectAllUnread(uid);
 			JSONArray joa=new JSONArray();
 			
 			for(ComNofVO cnvHistory:Historylist){
 				JSONObject job=new JSONObject(cnvHistory);
 				
 				joa.put(job);
 			
 			}
 			    jobMain.put("type", "history");
				jobMain.put("notifiArray", joa);
			
 			
		 			NofOnlineMap.get(uid).getAsyncRemote().sendText(jobMain.toString());
		 			
		

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		@OnMessage
		public void onMessage(@PathParam("uid") String uid,Session userSession, String message) {
			
	        this.NofOnlineMap.get(uid).getAsyncRemote().sendText("123");
			
		}
		
		@OnError
		public void onError(Session userSession, Throwable e){

		}
		
		@OnClose
		public void onClose(@PathParam("uid") String uid,Session userSession, CloseReason reason) {
			if(this.NofOnlineMap.contains(uid)){
				this.NofOnlineMap.remove(uid);
			}
			

		}
		
		public void SendNofToSomeOne(String uid, ComNofVO cnf){
			try {
			JSONObject jobMain=new JSONObject();
			JSONObject job=new JSONObject(cnf);
			JSONArray joa=new JSONArray();
		
				
				
				joa.put(job);
				jobMain.put("notifiArray",joa);
				jobMain.put("type", "history");
				if(NofOnlineMap.contains(uid)){
						NofOnlineMap.get(uid).getAsyncRemote().sendText(jobMain.toString());
					}
				else{
					
			    }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cvs.insert(cnf);

	}

	
}
