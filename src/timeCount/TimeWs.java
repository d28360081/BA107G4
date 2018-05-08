package timeCount;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

import org.json.JSONException;
import org.json.JSONObject;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidService;
import com.combid.model.ComBidVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;



@ServerEndpoint("/TimeWs/{uid}")
//用來通知成員代購,團購案件是否成立,上限投票,編輯代購訊息的WS
public class TimeWs {
	static ConcurrentHashMap<String, Session> OnlineMap=new ConcurrentHashMap<String, Session>();
    ComService csv=new ComService();
	ComPantLsService spls=new ComPantLsService();
	HashMap<String, ComPantLsVO> compantlsList=null;
	JSONObject job=null;
	
	@OnOpen
	public void onOpen(@PathParam("uid") String uid, Session userSession) throws IOException, JSONException {
	
		OnlineMap.put(uid, userSession);
	

		//糾團招募完成通知
//		countDownNotifi();
//		//每次上限啟動投票通知
		
	}

	// 此方法接收String型式資料
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		
	}

	// 此方法接收byte型式資料
	@OnMessage
	public void OnMessage(Session userSession, ByteBuffer bytes) {
		
		
		
		
	}
	public ConcurrentHashMap<String,Session> getMap(){
		return OnlineMap;
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
	}

	@OnClose
	public void onClose(@PathParam("uid") String uid,Session userSession, CloseReason reason) {
		this.OnlineMap.remove(uid);
	}
	
	//通知主辦人
	public void notifiTuanGoHost(String mem_id,String com_id) throws RuntimeException{
		Session hostSession=this.OnlineMap.get(mem_id);
		ComPantLsVO compantlsvo=spls.select(mem_id, com_id);
		
		
		job=new JSONObject();
		try {
			job.append("type", "voteConfirm");
			job.append("com_id", com_id);
			job.append("editUri", "http://localhost:8081/BA107G4Final/CombidController?com_id="+com_id+"&type=BiddingEdit");
			hostSession.getAsyncRemote().sendText(job.toString());
//			System.out.println("已送出host編輯__會員id:__"+mem_id+"_案件編號__:__"+com_id);
//			System.out.println("主辦人通知");
			compantlsvo.setMem_sts("已讀代購通知");
			spls.update(compantlsvo);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
		
		
	}
	//通知代購
	public void countDownNotifi2(String com_id) throws JSONException {
		
		ComPantLsService cpls=new ComPantLsService();
		//沒有投票的會員
		List<ComPantLsVO> unvoteList=spls.selectAllinCase(com_id);
		//選取案件
		ComVO cv=csv.select(com_id);
		
		//確定案件人數大於最小參與人數
		if(cpls.selectAllinCase(com_id).size()>=cv.getCom_min_num()) {
			
		for(ComPantLsVO cp:unvoteList) {
			
			      
					if(OnlineMap.containsKey(cp.getMem_id())) {
						
						  //如果是主辦人,傳送編輯代購訊息網址
						  if (cp.getMem_id().equals(cv.getMem_id())) {
							    job=new JSONObject();
							    job.append("type", "voteConfirm");
								job.append("com_id", com_id);
								job.append("comtit", cv.getCom_tit());
								job.append("editUri", "http://localhost:8081/BA107G4Final/CombidController?com_id="+com_id+"&type=BiddingEdit");
								
								if(OnlineMap!=null) {
								OnlineMap.get(cp.getMem_id()).getAsyncRemote().sendText(job.toString());
								}
						  }
						  //如果是一般參與會員
						  else{
							    JSONObject job2=new JSONObject();
								job2.put("type", "info");
								job2.put("cnt","恭喜案件:"+cv.getCom_tit()+"已經成立囉");
									if(OnlineMap!=null) {
									OnlineMap.get(cp.getMem_id()).getAsyncRemote().sendText(job2.toString());
									}
								}
						
					}
		}
		cv.setCom_sts("等待主辦人編輯代購訊息");
		csv.update(cv);
		//如果人數不足
		}else {
			for(ComPantLsVO cp:unvoteList) {
				if(OnlineMap.containsKey(cp.getMem_id())) {
					JSONObject joaa=new JSONObject();
					joaa.put("type", "info");
					joaa.put("cnt", "代購:"+cp.getCom_id()+"人數不足已遭下架");
					OnlineMap.get(cp.getMem_id()).getAsyncRemote().sendText(joaa.toString());
					
				}
			
			
			}
			
			cv.setCom_sts("下架");
			csv.update(cv);
			
		}
		
		
		
		
	}
	
	

//	public void countDownNotifi() throws RuntimeException{
//		try {
//			Object[] onlineMemberArray=OnlineMap.keySet().toArray();
//           
//			//每一個倒數結束的案件
//			int voteSize=voteMap.size();
//			for(int i =0;i<voteSize;i++){
//			 
//				Object[] iterator=voteMap.keySet().toArray();
//				
//				
//				compantlsList=spls.selectUnVoted();
//				
//		
//		 
//				Object[] unvotedMemberArray=compantlsList.keySet().toArray();
//				//每一個需要投票案件中,上未投票的會員
//				for(Object ob:unvotedMemberArray){
//					String unvoteMemberId=ob.toString();
//		
//					//從線上會員中,找出案件中未投票的會員
//					for(Object onlinemember:onlineMemberArray){
//				
//						if(onlinemember.toString().equals(unvoteMemberId.toString())){
//							
//							//從會員中找出代購創辦人給予投票編輯網址
//							if(csv.select(iterator[i].toString()).getMem_id().equals(unvoteMemberId.toString())){
////								System.out.println("主辦人取得");
//								notifiTuanGoHost(unvoteMemberId.toString(),iterator[i].toString());
//
//							}else{
//							
//						  
//								job=new JSONObject();
//								job.append("type", "ComeToVote");
//								job.append("com_id", iterator[i].toString());
//								OnlineMap.get(onlinemember).getAsyncRemote().sendText(job.toString());
//								
//								ComPantLsVO cplv=new ComPantLsVO();
//								cplv.setCom_id(iterator[i].toString());
//								cplv.setMem_id(onlinemember.toString());
//								cplv.setCom_it_num(0);
//								cplv.setMem_sts("已讀代購通知");
//						
//								spls.update(cplv);
//							
//							}
//							
//							
//							
//						}
//					}
//				}
//				
//				ComVO cvcv=voteMap.get(iterator[i]);
//				voteMap.remove(voteMap.get(iterator[i]));
//	            voteSize--;
//	       		
//			}					
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		
//	}
	
	//代購人投票通知
	public void notifiToVote(String com_id) {
        
			try {
				
				List<ComPantLsVO> list=spls.selectAllinCase(com_id);
				List<ComBidVO> list2=new ComBidService().selectAll(com_id);
				ComVO cv=new ComService().select(com_id);
				
				for(ComPantLsVO cplv:list) {
					
				  if(OnlineMap.containsKey(cplv.getMem_id())) {
			         if(list2.size()>0) {
					     job=new JSONObject();
					     job.append("type", "cometoVote");
					     job.append("com_id",com_id+",準備進行投票");
				         job.append("voteuri", "http://localhost:8081/BA107G4Final/com.combid.view/ComBidVote.jsp?Mem_id="+cplv.getMem_id()+"&com_id="+com_id);
				         if(OnlineMap.get(cplv.getMem_id())!=null) {
				        	
					         OnlineMap.get(cplv.getMem_id()).getAsyncRemote().sendText(job.toString());
					        
				         }
				         cv.setCom_sts("準備進行投票");
				         new ComService().update(cv);
			         }else {
			        	 
			        	 JSONObject joa=new JSONObject();
			        	 joa.put("type", "info");
			        	 joa.put("cnt", "代購:"+cv.getCom_tit()+"因無人競標,已經被系統下架");
			            
			        	 OnlineMap.get(cplv.getMem_id()).getAsyncRemote().sendText(joa.toString());
			        	 cv.setCom_sts("下架");
			        	 new ComService().update(cv);
			         }
				   }
				}
				
				
//				
//				ComBidService combidservice=new ComBidService();
//				
//				if(combidservice.selectAll(com_id).size()>0){
//				//代表全部人都已經投過票
//				if(list.size()==0){
//					List<ComPantLsVO> compantlsvolist=spls.selectAllinCase(com_id);
//				
//				
//				for(ComPantLsVO compantlsvo:compantlsvolist){
//					for(String onlineKey:this.OnlineMap.keySet()){
//						if(compantlsvo.getMem_id().equals(onlineKey)){
//							JSONObject job=new JSONObject();
//							job.append("type", "voteDone");
//							job.append("com_id", com_id);
//							job.append("voteuri", "http://localhost:8081/BA107G4/CombidController?type=voteDone&com_id="+com_id);
//							this.OnlineMap.get(onlineKey).getAsyncRemote().sendText(job.toString());
//						}
//					}
//				}
//				
//				
//				new TimeCountTool().getTimecounter().getDaiGoCountDownMap().remove(com_id);	
//				
//				}else{
//        
//			    //從案件中選出參與的會員 通知投票
//				for(ComPantLsVO compantlsvo:list) {
//					//餐與成員與線上成員比較
//					
//				  for(String s:OnlineMap.keySet()){
//					 
//					  //如果相等
//				    if(s.equals(compantlsvo.getMem_id())){
//				    
//					     String mem_id=compantlsvo.getMem_id();
//					     job=new JSONObject();
//					     job.append("type", "cometoVote");
//					     job.append("com_id",com_id+",準備進行投票");
//				         job.append("voteuri", "http://localhost:8081/BA107G4/com.combid.view/ComBidVote.jsp?Mem_id="+mem_id+"&com_id="+com_id);
//				         
//				         this.OnlineMap.get(mem_id).getAsyncRemote().sendText(job.toString());
//				        
//				   }
//				  }
//				}
//				}
//				//如果沒有人餐與競標
//			}else{
//				
//				
//				for(String onlineKey:this.OnlineMap.keySet()){
//					for(ComPantLsVO compantlsvo:spls.selectAllinCase(com_id)){
//						if(compantlsvo.getMem_id().equals(onlineKey)){
//							job=new JSONObject();
//							job.append("type", "RecruitFail");
//							job.append("com_id",com_id+",未有人參與競標");
//							job.append("voteuri", "#");
//							this.OnlineMap.get(onlineKey).getAsyncRemote().sendText(job.toString());
//						}
//					}
//				}
//	
//			}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
	    
	    //傳送一般通知訊息 
		public void sendInfo(String mem_id,String msgCnt) {
		
			if(OnlineMap.containsKey(mem_id)) {
				
				if(OnlineMap.get(mem_id)!=null) {
					JSONObject jobb=new JSONObject();
					System.out.println(1098);
					try {
						jobb.put("type", "info"); 
						jobb.put("cnt", msgCnt);
						OnlineMap.get(mem_id).getAsyncRemote().sendText(jobb.toString());
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
	
	
	
	
}
