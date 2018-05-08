package com.combid.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidService;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemService;
import com.member.model.MemVO;

import timeCount.TimeCountTool;
import timeCount.TimeWs;

/**
 * Servlet implementation class ComCorController
 */
@WebServlet("/ComCorController")
public class ComCorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComCorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String type=request.getParameter("type");
	 ComPantLsService cpls=new ComPantLsService();
	 ComService cs=new ComService();
	 ComBidService combidservice=new ComBidService();
	 ArticleService as=new ArticleService();
	 MemService ms=new MemService();
	 request.setCharacterEncoding("utf-8");
		if(type!=null&&"CorCheck".equals(type)) {
		 String com_id=request.getParameter("com_id");
		 String mem_id=request.getParameter("mem_id");
		 ComPantLsVO cplvcor=cpls.select(mem_id, com_id);
		 ComVO cv=cs.select(com_id);
		 cplvcor.setMem_sts("已收貨");
		 cpls.update(cplvcor);
		 request.setAttribute("ComVO", cv);
		 request.getRequestDispatcher("/com.combid.view/ratingPage.jsp").forward(request, response);
		 return; 
		 
	 }else if(type!=null&&"SanHo".equals(type)) {
		 

		 //評分
		 String rating=request.getParameter("rating");
		 String com_id=request.getParameter("com_id");
		 
		 //代購人
		 String bidderWinner=combidservice.selectBiddingWinner(com_id).getMem_id();
		 MemVO mv=ms.getOneMem(bidderWinner);
		 //評分的購買人
		 String rater=request.getParameter("mem_id");
		 //內容
		 String cnt=new String(request.getParameter("editor1").getBytes("ISO-8859-1"),"utf-8");

         
         //案件本身
		 ComVO cvNopic=cs.select(com_id);
		 ComVO cvwithPic=cs.selectpic(com_id);
		 //曬貨文章
		 ArticleVO av=new ArticleVO();
		 as.addArt(rater, "[曬貨文章]"+cvNopic.getCom_tit(), cnt, cvwithPic.getCom_pic1(), cvwithPic.getCom_pic2(), cvwithPic.getIt_chk_pic1());
		 System.out.println("cnt:"+cnt);
		 
		 //新增紅利
		 mv.setBonus(mv.getBonus()+(Integer.valueOf(rating)*50));
		 ms.updateBonus(bidderWinner, mv.getBonus()+(Integer.valueOf(rating)*50));
		 
		 
		 //發文通知
		 TimeWs tw=new TimeWs();
		 tw.sendInfo(bidderWinner, "會員:"+rater+"<br>在案件:"+cvNopic.getCom_tit()+"已經完成收貨,<br>前往文章專區看看曬貨文章");
		 request.getRequestDispatcher("/Com_Controller?type=checklist&page=1").forward(request, response);
		 TimeCountTool tct=new TimeCountTool();
		 tct.cancelTimer();
		
		 return;
	 }
		
		
		
		
		
		String com_id=request.getParameter("com_id");
		System.out.println("com_id"+com_id);
		
		List<ComPantLsVO> list=cpls.selectAllinCase(com_id);
		
		ComVO cv=cs.select(com_id);
				    for(ComPantLsVO cplv:list) {
				    	   System.out.println(456);
								if(request.getParameter(cplv.getMem_id())!=null) {
									System.out.println(123);
									cplv.setCor_id(request.getParameter(cplv.getMem_id()));
									cpls.update(cplv);
								}
		
				    }
				    cv.setCom_sts("運送中");
				    GroupEditWS gew =new GroupEditWS();
				    JSONObject job=new JSONObject();
				    JSONArray joa=new JSONArray();
				 
				    try {
				    	   job.put("type", "delivery");
						    joa.put(job);
						gew.broadcastInSameParty(joa.toString(), com_id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    cs.update(cv);
				    request.setAttribute("Message", "貨單更新完畢");
				    request.setAttribute("ComVO", cv);
				 request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);
				 return;
				    
		    }

}
