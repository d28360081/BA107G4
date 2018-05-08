package com.combid.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
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
import com.mysql.jdbc.util.Base64Decoder;
import com.sun.mail.util.BASE64DecoderStream;

@ServerEndpoint(value = "/GroupEditWS/{uid}/{comid}", configurator = ServletAwareConfig.class)
public class GroupEditWS {

	static ConcurrentHashMap<String, Session> OnlineHashMap = new ConcurrentHashMap<String, Session>();
	static HashMap<String, String> checkMap = new HashMap<String, String>();
	static ConcurrentHashMap<String, Session> AndroidMap = new ConcurrentHashMap<String, Session>();
	private EndpointConfig Userconfig;
	String imgstring=null;
	int imgcount=0;

	@OnOpen
	public void onOpen(@PathParam("uid") String uid, @PathParam("comid") String comid, Session userSession,
			EndpointConfig config) throws IOException {
		userSession.setMaxBinaryMessageBufferSize(1024*1024*30);
	     	System.out.println("+++++++++++++onOpen++++++++++++++");
		if (config.getUserProperties().get("httpSession") != null) {
			
			NofWS nofws = new NofWS();
			ComPantLsService compantlsservice = new ComPantLsService();
			List<ComPantLsVO> list = compantlsservice.selectAllinCase(comid);
			ComService comservice = new ComService();
			ComNofService cns = new ComNofService();
			JSONArray joa = new JSONArray();
			MemService memservice=new MemService();
			OnlineHashMap.put(uid, userSession);
			// 傳圖片

			try {
				if (compantlsservice.select(uid, comid) != null) {
					
					
					JSONObject joc = new JSONObject();
					byte[] picByte = comservice.selectpic(comid).getIt_chk_pic1();
					Encoder encoder = Base64.getEncoder();
					// 如果確認圖片不為空直
					if (picByte != null) {
						String answer = compantlsservice.select(uid, comid).getMem_sts();
						String picString = encoder.encodeToString(picByte);
						StringBuffer sb = new StringBuffer();
						sb.append("data:image/png;base64,");
						sb.append(picString);
						// 如果投過票
						joc.put("type", "buyerCheck");
						if ("True".equals(answer) || "False".equals(answer)) {
							joc.put("answer", "voted");
							// 尚未投票
						} else {
							joc.put("answer", "unvoted");
						}

						joc.put("pic", sb.toString());
						joa.put(joc);
					}
					// 如果不是參加會員,判斷是否為代購人
				} else{
					
					JSONObject joc = new JSONObject();
					byte[] picByte = comservice.selectpic(comid).getIt_chk_pic1();
					Encoder encoder = Base64.getEncoder();
					if (picByte != null) {
						// 圖片解碼
						String picString = encoder.encodeToString(picByte);
						StringBuffer sb = new StringBuffer();
						sb.append("data:image/png;base64,");
						sb.append(picString);

						joc.put("type", "buyerCheck");
						joc.put("answer", "unvoted");
						joc.put("pic", sb.toString());
						joa.put(joc);
					}
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//////////////////////////////////////////////
			if ("開始購買".equals(comservice.select(comid).getCom_sts())||"運送中".equals(comservice.select(comid).getCom_sts())) {
				JSONObject joc = new JSONObject();

				try {
					joc.put("type", "BuyerCheckReply");
					joc.put("reponse", "true");
					if("運送中".equals(comservice.select(comid).getCom_sts())) {
						joa.put(new JSONObject().put("type", "delivery"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				joa.put(joc);
			} else {
				JSONObject joc = new JSONObject();

				try {
					joc.put("type", "BuyerCheckReply");
					joc.put("reponse", "false");
					   
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//避免頁面重刷的狀況
				for(ComPantLsVO cplv:compantlsservice.selectAllinCase(comid)){
					System.out.println(123);
					cplv.setCom_sts("已投票");
					compantlsservice.update(cplv);
				}
				joa.put(joc);
			}

			//////////////////////////////////////////////
			int count = 0;
			// 代購確認
			// if(this.checkMap.containsKey(comid)){
			// JSONObject job=new JSONObject();
			// try {
			// job.put("type", "buyerCheck");
			// job.put("pic", checkMap.get(comid));
			// joa.put(job);
			// } catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// }
			//

			// 過往的提醒
			for (ComPantLsVO cpl : list) {
				if (cpl.getCom_it_num() != 0) {
					JSONObject job = new JSONObject();
					try {
						job.put("mem_id", memservice.getOneMem(cpl.getMem_id()).getAcc());
						job.put("type", "payUpdate");
						joa.put(job);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;

				} else {

				}

			}

			if (count == list.size()) {
				JSONObject job = new JSONObject();
				try {
					job.put("type", "stopNumberUpdate");
					joa.put(job);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			List<ComNofVO> noflist = cns.selectCom_Nof(comid);

			// 留言簿分
			for (ComNofVO cnv : noflist) {

				try {
					JSONObject job = new JSONObject();
					job.put("mem_id", cnv.getMem_id());
					job.put("mem_acc", memservice.getOneMem(cnv.getMem_id()).getAcc());
					job.put("nof_cnt", cnv.getNof_cnt());
					job.put("type", "Comment");
					joa.put(job);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			
			if (joa.length() != 0) {
			
				this.OnlineHashMap.get(uid).getAsyncRemote().sendText(joa.toString());
				
			}
		} else if (config.getUserProperties().get("httpSession") == null) {
			userSession.setMaxBinaryMessageBufferSize(1024*1024*5);
			this.AndroidMap.put(uid, userSession);
			sendtoOnlineInfoAndroid(uid, comid);

		}

	}

	@OnMessage
	public void onMessage(@PathParam("uid") String uid, Session userSession, String message,@PathParam("comid") String comid) {
		try {
	
		MemService ms=new MemService();
        ComService cv=new ComService();
		JSONObject job = null;
		ComNofService comnofservice = new ComNofService();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+message.length());

	

			// String str = message.replace("\\", "");
			// StringBuffer sb = new StringBuffer(str);
			// sb.deleteCharAt(0).deleteCharAt(sb.length() - 1);
		if(message.length()<700) {
			job = new JSONObject(message.replace("\\", "").replace("\"", ""));
		
			ComPantLsService compantlsservice = new ComPantLsService();

			JSONArray jos = new JSONArray();
			if ("numberSend".equals(job.get("type"))) {

				// 新增商品,新增數量,新增付款方式
				// System.out.println(job.getString("mem_id"));
				// System.out.println(job.getString("com_id"));
				// System.out.println(job.getString("com_py"));
				// System.out.println(job.getString("com_it_num"));
				ComPantLsVO compantlsvo = compantlsservice.select(job.get("mem_id").toString(), job.get("com_id").toString());
				compantlsvo.setCom_py(job.get("com_py").toString());
				compantlsvo.setMem_sts("已更新資料");
				compantlsvo.setCom_sts("已付款");
				compantlsvo.setCom_it_num(Integer.valueOf(job.get("com_it_num").toString()));
				compantlsvo.setDlt_adds(job.get("dlt_adds").toString());
				compantlsservice.update(compantlsvo);
				// 通知代購人

				ComNofVO cnv = new ComNofVO();
				cnv.setCom_id(job.get("com_id").toString());
				cnv.setMem_id(job.get("mem_id").toString());
				cnv.setNof_cnt(job.get("mem_id").toString() + "已經更新商品數量,付款方式以及地址");
				cnv.setNof_tit("代購編號" + job.get("com_id").toString() + "數量更新通知");
				cnv.setNof_sts("未讀");

				comnofservice.insert(cnv);

				job.remove("type");
				job.put("type", "numberConfirm");
				jos.put(job);
				this.broadcastInSameParty(jos.toString(), job.get("com_id").toString());

				int count = 0;
				for (ComPantLsVO cmtlv : compantlsservice.selectAllinCase(job.get("com_id").toString())) {
					if (cmtlv.getCom_it_num() != 0) {

					} else {
						count++;
					}

				}

				if (count == 0) {

					ComBidService combidservice = new ComBidService();

					ComBidVO combidvowinner = combidservice.selectBiddingWinner(job.get("com_id").toString());

					ComNofVO comnofvo = new ComNofVO();
					comnofvo.setMem_id(combidvowinner.getMem_id());
					comnofvo.setNof_cnt("編號:" + job.get("com_id").toString() + "已經更新完成數量");
					comnofvo.setNof_sts("未讀");
					comnofvo.setNof_tit("代購人數量更新完成");
					comnofvo.setCom_id(job.get("com_id").toString());
					comnofservice.insert(comnofvo);
					JSONArray joa = new JSONArray();
					JSONObject jobStp = new JSONObject();
					jobStp.append("type", "stopNumberUpdate");
					joa.put(jobStp);
					this.broadcastInSameParty(joa.toString(), job.get("com_id").toString());

				}

			} else if ("rmdsend".equals(job.get("type"))) {
				ComNofVO comnofvo = new ComNofVO();
				ComNofService cns = new ComNofService();
				comnofvo.setCom_id(job.get("com_id").toString());
				comnofvo.setMem_id(job.get("mem_id").toString());
				comnofvo.setNof_cnt(job.get("rmd").toString());
				comnofvo.setNof_tit("留言");
				comnofvo.setNof_sts("代購留言");
				cns.insert(comnofvo);
				// 接受訊息後傳送
				JSONArray joa = new JSONArray();
				JSONObject jobmsg = new JSONObject();
				jobmsg.append("mem_acc",ms.getOneMem(job.get("mem_id").toString()).getAcc());
				jobmsg.append("mem_id",job.get("mem_id").toString());
				jobmsg.append("nof_cnt",job.get("rmd").toString());
				jobmsg.append("type", "Comment");
				joa.put(jobmsg);

				this.broadcastInSameParty(joa.toString(), job.get("com_id").toString());

			}
			// 競標者 傳重要訊息
			else if ("BuyerNotifi".equals(job.get("type"))) {

			}
				}
		
		//如果是圖片的話
				else {
					Decoder base64decode=Base64.getDecoder();
					String pic=message.substring(message.indexOf(",")+1,message.length()-4).replace(" ","");
					
					byte[] picbyte=base64decode.decode(pic.trim());
					ComVO comvo=cv.select(comid);
					comvo.setCom_sts("正在確認");
		    		
		    		comvo.setIt_chk_pic1(picbyte);
		    		ComVO comPic2=cv.selectpic(comid);
		    		comvo.setCom_pic1(comPic2.getCom_pic1());
		    		comvo.setCom_pic2(comPic2.getCom_pic2());
		    		cv.updatePIC(comvo);
//		            System.out.println(newpic.substring(22).replace(" ", ""));
		            comvo.setIt_chk_pic1(picbyte);
		            cv.update(comvo);
					JSONObject job2=new JSONObject();
					JSONArray joaa2=new JSONArray();
					job2.put("mem_id", "123");
					
					job2.put("upload", "yes");
					job2.put("answer", "unvoted");
					job2.put("nof_cnt", "111");
					job2.put("pic", "data:image/jpg;base64,"+pic);
					job2.put("type", "buyerCheck");
					joaa2.put(job2);
					
					System.out.println(joaa2.toString());
					System.out.println(joaa2.length());
				    System.out.println("picLoad!!");
				 synchronized(this){
					 
				    if(imgcount==0){
				    	System.out.println("firsttime");
					imgstring=joaa2.toString();
				   	broadcastInSameParty(joaa2.toString(),comid);
				   	imgcount++;
				   
				   }else if(imgcount!=0){
					   if(joaa2.toString().equals(imgstring)){
						   System.out.println("repeat");
					   }else if(!joaa2.toString().equals(imgstring)){
						   System.out.println("not same pic");
						   imgstring=joaa2.toString();
						   broadcastInSameParty(joaa2.toString(),comid);
					   }
				   }
				    
				  }
				
				}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}

	@OnClose
	public void onClose(@PathParam("uid") String uid, Session userSession, CloseReason reason) {
		System.out.println(reason);
		if(OnlineHashMap.containsKey(uid)) {
		this.OnlineHashMap.remove(uid);
		}
		if(AndroidMap.contains(uid)) {
		AndroidMap.remove(uid);
		}
		
		System.out.println("+++++++++++++onClose++++++++++++++");

	}

	public void broadcast(String msg) {
		for (String onlinename : this.OnlineHashMap.keySet()) {

			this.OnlineHashMap.get(onlinename).getAsyncRemote().sendText(msg);
		}

	}

	synchronized public void broadcastInSameParty(String msg, String com_id) throws Exception {
		try {
			
	
			ComPantLsService cps = new ComPantLsService();
			ComBidService cbs = new ComBidService();
			List<ComPantLsVO> list = cps.selectAllinCase(com_id);
			String winnerid = cbs.selectBiddingWinner(com_id).getMem_id();
			// 給團體成員
			for (ComPantLsVO cplv : list) {
				String mem_id = cplv.getMem_id();
				// System.out.println(OnlineHashMap);
				// System.out.println(OnlineHashMap.containsKey(mem_id)+"__會員__:"+mem_id);
				// if(this.Userconfig!=null){
				//				System.out.println("winner_id:_"+winnerid);
					//				System.out.println("mem_id:_"+cplv.getMem_id());
				//				System.out.println(!mem_id.equals(winnerid));
				//				System.out.println(OnlineHashMap.get(mem_id));
				
				if (!mem_id.equals(winnerid)&&OnlineHashMap.containsKey(cplv.getMem_id())) {
                         
					OnlineHashMap.get(mem_id).getAsyncRemote().sendText(msg);
                    System.out.println(9999);
                    
				} else if (AndroidMap.get(mem_id)!= null&&AndroidMap.containsKey(mem_id)) {
					
                    int count=0;
					JSONArray job = new JSONArray(msg);
					for (int i = 0; i < job.length(); i++) {
						if ((((JSONObject)job.get(i)).get("type")).equals("buyerCheck")) {
							
							((JSONObject)job.get(i)).remove("pic");
						     
				
						}

					}
					
						JSONObject jobb=new JSONObject();
						jobb.put("array", job);
						System.out.println("abe"+jobb.toString());
						
						this.AndroidMap.get(mem_id).getAsyncRemote().sendText(jobb.toString());
						System.out.println(99991);

				}

				//
			}

			// 給得標人
             
			

			if (winnerid != null) {

				if (this.OnlineHashMap.containsKey(winnerid)) {
					this.OnlineHashMap.get(winnerid).getAsyncRemote().sendText(msg);
					System.out.println(999994);
				} else if (this.AndroidMap.get(winnerid) != null&&this.AndroidMap.containsKey(winnerid)) {

					try {
						JSONObject job = new JSONObject(msg);

						if (job.get("type").equals("buyerCheck")) {
							job.remove("pic");
							this.AndroidMap.get(winnerid).getAsyncRemote().sendText(job.toString());
							System.out.println(99992);
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendtoOnlineInfoAndroid(String uid, String comid) {
		try {
			ComPantLsService compantlsservice = new ComPantLsService();
			List<ComPantLsVO> list = compantlsservice.selectAllinCase(comid);
			ComBidService cbs = new ComBidService();
			ComService comservice = new ComService();
			ComNofService cns = new ComNofService();
			JSONObject joa = new JSONObject();
			JSONObject daiGoArray = new JSONObject();
			JSONArray tuanGOArray = new JSONArray();
			JSONArray chatArray = new JSONArray();
			ComVO comvo = comservice.select(comid);
			MemService ms = new MemService();
			ComNofService nof = new ComNofService();
			List<ComNofVO> chatList = nof.selectCom_Nof(comid);
			ComBidVO cbv = cbs.selectBiddingWinner(comid);
			MemVO memvo = null;

			// 判斷腳色
			for (ComNofVO cnv : chatList) {
				memvo = ms.getOneMem(cnv.getMem_id());
				JSONObject job = new JSONObject();
				job.put("acc", memvo.getAcc());
				job.put("mem_id", memvo.getMem_id());
				job.put("cnt", cnv.getNof_cnt());
				// 如果是主辦人
				if (comvo.getMem_id().equals(cnv.getMem_id())) {
					job.put("role", "host");
					// 代購人
				} else if (cbv.getMem_id().equals(cnv.getMem_id())) {
					job.put("role", "agent");
					// 一般代購會員
				} else {
					job.put("role", "member");
				}

				chatArray.put(job);
			}

			// //團購名單
			for (ComPantLsVO cpl : list) {

				JSONObject job = new JSONObject();
				memvo = ms.getOneMem(cpl.getMem_id());
				job.put("mem_id", memvo.getMem_id());
				job.put("acc", memvo.getAcc());
				String checkAnswer = cpl.getMem_sts();

				// 確認商品?
				if ("True".equals(checkAnswer)) {
					job.put("check", "positive");
				} else if ("False".equals(checkAnswer)) {
					job.put("check", "negative");
				}else {
					job.put("check", "never");
				}
				// 更新數量了嗎?
				if (cpl.getCom_it_num() == 0) {
					job.put("checkNum", "negative");
				} else {
					job.put("checkNum", "positive");
				}

				// 是主辦人的話
				if (comvo.getMem_id().equals(cpl.getMem_id())) {
					job.put("host", "positive");

				} else {
					job.put("host", "negative");
				}
				tuanGOArray.put(job);

			}

			JSONObject jobWinner = new JSONObject();
			// 代購go

			memvo = ms.getOneMem(cbv.getMem_id());
			daiGoArray.put("mem_id", cbv.getMem_id());
			daiGoArray.put("acc", memvo.getAcc());
			// 代購人上傳圖片了嗎?
			if (comservice.selectpic(comid).getIt_chk_pic1() != null) {

				daiGoArray.put("upload", "yes");

			} else {
				daiGoArray.put("upload", "no");
			}

			joa.put("daiGOArray", daiGoArray);
			joa.put("tuanGOArray", tuanGOArray);
			joa.put("chatArray", chatArray);
			joa.put("com_id", comid);

			if (AndroidMap.get(uid) != null) {
				System.out.println("1" + joa.toString());
				AndroidMap.get(uid).getAsyncRemote().sendText(joa.toString());
				// if(daiGoArray.length()!=0&&tuanGOArray.length()!=0&&chatArray.length()!=0) {
				// System.out.println("2"+joa.toString());
				// AndroidMap.get(uid).getAsyncRemote().sendText(joa.toString());
				//
				// }

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}