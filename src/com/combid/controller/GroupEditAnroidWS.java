package com.combid.controller;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
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

import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidService;
import com.combid.model.ComBidVO;
import com.comnof.controller.NofWS;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemService;
import com.member.model.MemVO;



@ServerEndpoint(value="/GroupEditAnroidWS/{uid}/{comid}")
public class GroupEditAnroidWS {
	
		

	static ConcurrentHashMap<String, Session> OnlineHashMap=GroupEditWS.OnlineHashMap;
	static HashMap<String, String> checkMap=GroupEditWS.checkMap;
	static ConcurrentHashMap<String, Session> AndroidMap=new ConcurrentHashMap<String, Session>() ;
	GroupEditWS gew=new GroupEditWS();
	@OnOpen
	public void onOpen(@PathParam("uid") String uid,@PathParam("comid") String comid, Session userSession) throws IOException {
		  try {
	   AndroidMap.put(uid, userSession);
	   System.out.println("aab"+OnlineHashMap.get(uid)==null);
       NofWS nofws=new NofWS();
       ComPantLsService compantlsservice=new ComPantLsService();
       List<ComPantLsVO> list= compantlsservice.selectAllinCase(comid);
       ComBidService cbs=new ComBidService();
       ComService comservice=new ComService();
       ComNofService cns=new ComNofService();
       JSONObject joa=new JSONObject();
       JSONObject daiGoArray=new JSONObject();
       JSONArray tuanGOArray=new JSONArray();
       JSONArray chatArray=new JSONArray();
       ComVO comvo=comservice.select(comid);
       MemService ms=new MemService();
       ComNofService nof=new ComNofService();
       List<ComNofVO>chatList=nof.selectCom_Nof(comid);
       ComBidVO cbv=cbs.selectBiddingWinner(comid);
       MemVO memvo=null;
       
       //判斷腳色
        for(ComNofVO cnv:chatList){ 
        	   memvo=ms.getOneMem(cnv.getMem_id());
        	  JSONObject job=new JSONObject();
        	  job.put("acc", memvo.getAcc());
        	  job.put("cnt", cnv.getNof_cnt());
        	  //如果是主辦人
        	  if(comvo.getMem_id().equals(cnv.getMem_id())){
        		  job.put("role", "host");
        		  //代購人
        	  }else if(cbv.getMem_id().equals(cnv.getMem_id())){
        		  job.put("role", "agent");
        	  //一般代購會員
        	  }else{
        		  job.put("role", "member");
        	  }
        	  
        	  chatArray.put(job);
        }
   
       
// 
//               //團購名單
			      for(ComPantLsVO cpl:list){
			    	  
			    	  JSONObject job=new JSONObject();
			    	  memvo=ms.getOneMem(cpl.getMem_id());
			    	  job.put("mem_id", memvo.getMem_id());
			    	  job.put("acc", memvo.getAcc());
			    	  String checkAnswer=cpl.getMem_sts();
			    	  
			    	  //確認商品?
			    	  if("True".equals(checkAnswer)){
			    		  job.put("check", "positive");
			    	  }else if("False".equals(checkAnswer)){
			    		  job.put("check", "negative");
			    	  }
			    	  //更新數量了嗎?
			    	  if(cpl.getCom_it_num()==0){
			    		  job.put("checkNum", "negative");
			    	  }else{
			    		  job.put("checkNum", "positive");
			    	  }
			    	 
			    	  
			    	  //是主辦人的話
			    	  if(comvo.getMem_id().equals(cpl.getMem_id())){
			    		  job.put("host", "positive");
			    		  
			    	  }else{
			    		  job.put("host", "negative");
			    	  }
			    	  tuanGOArray.put(job);
			    	  
			      }
			      
			     
			      JSONObject jobWinner=new JSONObject();
			      //代購go
			     
			      memvo=ms.getOneMem(cbv.getMem_id());
			      daiGoArray.put("mem_id",cbv.getMem_id());
			      daiGoArray.put("acc", memvo.getAcc());
	                 //代購人上傳圖片了嗎?
				      if(comservice.selectpic(comid).getIt_chk_pic1()!=null){
				    	
				    	  daiGoArray.put("upload", "yes");
						
				      }else {
				    	  daiGoArray.put("upload", "no");
				      } 
			      
			      
			      joa.put("daiGOArray",daiGoArray);
			      joa.put("tuanGOArray",tuanGOArray);
			      joa.put("chatArray",chatArray);
			      joa.put("com_id",comid);
			      
							if(daiGoArray.length()!=0&&tuanGOArray.length()!=0){	      
								OnlineHashMap.get(uid).getAsyncRemote().sendText(joa.toString());
						      
							}
							
							 
					
							
							
							
		  } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	@OnMessage
	public void onMessage(@PathParam("uid") String uid,Session userSession, String message) {
		ComNofService comnofservice=new ComNofService();
		  MemService ms=new MemService();
		JSONObject job=null;
		try {
		
		job=new JSONObject(message);
		ComPantLsService compantlsservice=new ComPantLsService();
		
		JSONArray jos=new JSONArray();
		 //付款資訊,代購數量上傳
		if("numberSend".equals(job.get("type"))){
		
		//新增商品,新增數量,新增付款方式	

		ComPantLsVO compantlsvo=compantlsservice.select(job.getString("mem_id"), job.getString("com_id"));
	    compantlsvo.setCom_py(job.getString("com_py"));
	    compantlsvo.setMem_sts("已更新資料");
	    compantlsvo.setCom_sts("已付款");
	    compantlsvo.setCom_it_num(Integer.valueOf(job.getString("com_it_num")));
	    compantlsvo.setDlt_adds(job.getString("dlt_adds"));
	    compantlsservice.update(compantlsvo);
	    //通知代購人
	    
	    ComNofVO cnv=new ComNofVO();
	    cnv.setCom_id(job.getString("com_id"));
	    cnv.setMem_id(job.getString("mem_id"));
	    cnv.setNof_cnt(job.getString("mem_id")+"已經更新商品數量,付款方式以及地址");
	    cnv.setNof_tit("代購編號"+job.getString("com_id")+"數量更新通知");
	    cnv.setNof_sts("未讀");
	    
	    comnofservice.insert(cnv);
	    
	    
	    job.remove("type");
	    job.put("type", "numberConfirm");
	    jos.put(job);
	    this.broadcasttoAndroid(jos.toString(), job.getString("com_id"));
	    
	  
	    int count=0;
	    for(ComPantLsVO cmtlv:compantlsservice.selectAllinCase(job.getString("com_id"))){
	    	if(cmtlv.getCom_it_num()!=0){

	    	}else{
		    	count++;
	    	}

	    }
     
	    if(count==0){
	    	
	    	ComBidService combidservice=new ComBidService();
	    	
	    	 ComBidVO combidvowinner= combidservice.selectBiddingWinner(job.getString("com_id"));
	    	
	    		
	    			ComNofVO comnofvo=new ComNofVO();
	    			comnofvo.setMem_id(combidvowinner.getMem_id());
	    			comnofvo.setNof_cnt("編號:"+job.getString("com_id")+"已經更新完成數量");
	    			comnofvo.setNof_sts("未讀");
	    			comnofvo.setNof_tit("代購人數量更新完成");
	    			comnofvo.setCom_id(job.getString("com_id"));
		    		comnofservice.insert(comnofvo);
	    			JSONArray joa=new JSONArray();
	    			JSONObject jobStp=new JSONObject();
	    			jobStp.append("type", "stopNumberUpdate");
	    			joa.put(jobStp);
	    			gew.broadcastInSameParty(joa.toString(), job.getString("com_id"));
	    			
	    }
		
	  //代購留言
	}else if("rmdsend".equals(job.get("type"))){
		ComNofVO comnofvo=new ComNofVO();
		ComNofService cns=new ComNofService();
		comnofvo.setCom_id(job.getString("com_id"));
	    comnofvo.setMem_id(job.getString("mem_id"));
	    comnofvo.setNof_cnt(job.getString("rmd"));
	    comnofvo.setNof_tit("留言");
	    comnofvo.setNof_sts("代購留言");
	    cns.insert(comnofvo);
	    //接受訊息後傳送
	    JSONArray joa=new JSONArray();
  		JSONObject jobmsg=new JSONObject();
  		jobmsg.append("mem_id", ms.getOneMem(job.getString("mem_id")).getAcc());
  		jobmsg.append("nof_cnt", job.getString("rmd"));
  		jobmsg.append("type", "Comment");
		joa.put(jobmsg);
	   

		gew.broadcastInSameParty(joa.toString(), job.getString("com_id"));
	   
	    
	}
	//競標者ˊ傳重要訊息	
	else if ("BuyerNotifi".equals(job.get("type"))){

	}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
      e.printStackTrace();
	}
	
	@OnClose
	public void onClose(@PathParam("uid") String uid,Session userSession, CloseReason reason) {
		GroupEditWS.OnlineHashMap.remove(uid);
	    	    
	}
	

	public void broadcasttoAndroid(String mem_id,String msg){
		if(GroupEditWS.OnlineHashMap.contains(mem_id)){
			GroupEditWS.OnlineHashMap.get(mem_id).getAsyncRemote().sendText(msg);
		}
		
	}

 
}