package com.combid.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.comauth.model.ComQrVO;
import com.combid.model.ComBidService;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;

import timeCount.QRCodeGenerator;
import timeCount.TimeWs;

/**
 * Servlet implementation class ComGroupEditController
 */
@WebServlet("/ComGroupEditController")
public class ComGroupEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComGroupEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		ComService comservice=new ComService();
		if("GroupEdit".equals(type)){
			request.setAttribute("type", "GroupEdit");
            ComVO comvo=comservice.select(request.getParameter("com_id"));
			request.setAttribute("ComVO", comvo);
			request.getRequestDispatcher("/Com/Com_Frame.jsp").forward(request, response);
			return;
		}
		else if("BiddingWinnerEdit".equals(type)){
			request.setAttribute("type", "GroupEdit");
            ComVO comvo=comservice.select(request.getParameter("com_id"));
			request.setAttribute("ComVO", comvo);
			request.getRequestDispatcher("/Com/Com_Frame.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    JSONObject job2=null;

		    String com_id=request.getParameter("com_id");
		  
		    ComService comservice=new ComService();
		    ComPantLsService compantlsservice=new ComPantLsService();
		    ComBidService combidservice=new ComBidService();
		    GroupEditWS groupeditws=new GroupEditWS();
		    
		    //買家確認商品內容的答覆處理
		    if("BuyerCheck".equals(request.getParameter("type"))){
		    	ComPantLsVO compantlsvo=compantlsservice.select(request.getParameter("mem_id"), com_id);
		        TimeWs tw=new TimeWs();
//		    	System.out.println(request.getParameter("mem_id"));
//		    	System.out.println(com_id);
		    	System.out.println(request.getParameter("answer"));
		    	//如果是確認
		
		    	if("true".equals(request.getParameter("answer"))){
		    		 
		    	    compantlsvo.setMem_sts("True");
		    	
		    	    
		    	}else{
		    		 
		    		compantlsvo.setMem_sts("False");
		    	}
		    	
		    	compantlsservice.update(compantlsvo);
		    	     //每次確認後確定人數
		    	List<ComPantLsVO> pantListAll =compantlsservice.selectAllinCase(com_id);
		    	int count=0;
		    	int neCount=0;
                     for(ComPantLsVO cplv:pantListAll){             
                    	 if("True".equals(cplv.getMem_sts())){
                    		 count++;
                    	 }else if("False".equals(cplv.getMem_sts())){
                    		 neCount++;
                    	 }
                     }
             
                   //如果超過1/2,通知代購人可以購買可以購買  
                try {  
                     ComNofVO comvofvo=new ComNofVO();
                	 ComNofService comnofservice=new ComNofService();
                	 String winnerid=combidservice.selectBiddingWinner(com_id).getMem_id();
                	 JSONObject job=new JSONObject();
                	 JSONArray joa=new JSONArray();
                	 job.put("type", "BuyerCheckReply");
                
                 if(count>=pantListAll.size()/2){    	
                	 //更新案件進度
                
                	 ComVO comvo=comservice.select(com_id);
                	 comvo.setCom_sts("開始購買");
                	 comvofvo.setMem_id(winnerid);
                	 comvofvo.setNof_tit("代購購買開始通知");
                	 comvofvo.setNof_cnt("您在案件:"+comvo.getCom_tit()+"所po的商品,已經獲得1/2的參與人確認<br>現在已經可以開始購買");
                	 comvofvo.setNof_sts("未讀");
                	 comnofservice.insert(comvofvo);
                	 tw.sendInfo(winnerid, "您在案件編號:"+comvo.getCom_tit()+"所po的商品,已經獲得1/2的參與人確認<br>現在已經可以開始購買");
                	//產生qrcode
                	 QRCodeGenerator qg=new  QRCodeGenerator();
                	 List<ComPantLsVO> list2=compantlsservice.selectAllinCase(comvo.getCom_id());
                	 StringBuffer sb=new StringBuffer();
                	 
                	 sb.append(request.getScheme());
                	 sb.append("://");
                	 sb.append(request.getServerName());
                	 sb.append(":");
                	 sb.append(request.getServerPort());
                	 sb.append(request.getContextPath());
                	 
                	 for(ComPantLsVO cplv:list2){
                		 ComQrVO comqrvo=new ComQrVO();
                		 qg.pswGenerate(sb.toString(),com_id,cplv.getMem_id());
                		 
                	 }
                		
                	 comservice.update(comvo); 
					 job.put("reponse","true");
				     
                	 //未通過,則重新尋找物件
                 }else if(neCount>=pantListAll.size()/2){
                	 
                	 ComVO comvo=comservice.select(com_id);
                	 comvofvo.setMem_id(winnerid);
                	 comvofvo.setNof_tit("代購購買開始通知");
                	 comvofvo.setNof_cnt("您在案件編號:"+com_id+"所po的商品,未獲得1/2的參與人確認<br>請再次上傳");
                	 comvofvo.setNof_sts("未讀");
                	 comnofservice.update(comvofvo);
                	 tw.sendInfo(winnerid, "您在案件編號:"+comvo.getCom_tit()+"所po的商品,未獲得1/2的參與人確認");
                	 
                	 for(ComPantLsVO cplv:pantListAll){  
                		 cplv.setMem_sts("已投票");
                		 compantlsservice.update(cplv);
                		 System.out.println("voted"+cplv.getMem_id());
                	 }
                	 
					 job.put("reponse","false");

		
					 
                 }
                 
                 joa.put(job);
                 //如果代購人在線上的話
                 
		         if(groupeditws.OnlineHashMap.containsKey(winnerid)){
		        	 
		        	 groupeditws.OnlineHashMap.get(winnerid).getAsyncRemote().sendText(joa.toString());
		         }
		         
                } catch (JSONException e) {
					e.printStackTrace();
				}
		    
                return;
		    }
		    //確認結束
		    
		    
		    
		if("picupload".equals(request.getParameter("type"))){    
		 try {
			
			
			ComVO comvo=comservice.select(request.getParameter("com_id"));
			
			String pic=request.getParameter("img");
	        System.out.println(pic);
			int a=pic.indexOf(",");
			String newpic=pic.substring(0,pic.length()-2);
			//websocket實體
			GroupEditWS gew=new GroupEditWS();
			Decoder decode=Base64.getDecoder();
			Encoder encode=Base64.getEncoder();
		    gew.checkMap.put(request.getParameter("com_id"), newpic.replace(" ", ""));
		    //去掉字串取得檔案陣列
		    System.out.println(pic);
		    int dotPosition=newpic.indexOf(",");
            byte[] picByte=decode.decode(newpic.substring(dotPosition+1).replace(" ", ""));

            //將圖片放入儲存
    		comvo.setCom_sts("正在確認");
    		
    		comvo.setIt_chk_pic1(picByte);
    		ComVO comPic2=comservice.selectpic(request.getParameter("com_id"));
    		comvo.setCom_pic1(comPic2.getCom_pic1());
    		comvo.setCom_pic2(comPic2.getCom_pic2());
            comservice.updatePIC(comvo);
//            System.out.println(newpic.substring(22).replace(" ", ""));
            comvo.setIt_chk_pic1(picByte);
            comservice.update(comvo);
			JSONObject job=new JSONObject();
			JSONArray joa=new JSONArray();
			job.put("mem_id", "123");
			job.put("type", "buyerCheck");
			job.put("upload", "yes");
			job.put("answer", "unvoted");
			job.put("nof_cnt", "111");
			job.put("pic", newpic.replace(" ", ""));
			joa.put(job);
		    System.out.println("picLoad!!");
			gew.broadcastInSameParty(joa.toString(), request.getParameter("com_id"));
		    
		 
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	}
}
