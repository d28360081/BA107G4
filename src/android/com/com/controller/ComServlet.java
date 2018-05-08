package android.com.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.com.com.model.ComService;
import android.com.com.model.ComVO;
import android.com.combid.model.ComBidVO;
import android.com.compant.model.ComPantLsService;
import android.com.compant.model.ComPantLsVO;
import android.com.main.ImageUtil;
import android.com.member.model.MemberVO;
import android.com.promotion.model.PromotionService;
import android.com.promotion.model.PromotionVO;
import timeCount.TimeWs;


public class ComServlet extends HttpServlet {
	private static final String contentType = "text/html;charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String outStr = "";
		ComService comSvc = new ComService();

		Gson gson = new Gson();
		String action = req.getParameter("action");
		System.out.println(action);

		// 動作比對
		if ("getSingleCase".equals(action)) {
			// 選代購

			List<ComVO> comList = comSvc.selectWithAllComCase();
			outStr = gson.toJson(comList);

		} else if ("getGroupBuyCase".equals(action)) {
			// 選團購
			List<ComVO> comList = comSvc.selectWithAllGroupBuyCase();
			outStr = gson.toJson(comList);
		} else if ("insert".equals(action)) {
			// 建立代購案件
			// 拿到代購案件物件
			ComVO comvo = gson.fromJson(req.getParameter("comVO"), ComVO.class);
			//長林以後整合要打開
			//長林以後整合要打開
			//長林以後整合要打開
			//長林以後整合要打開
			//new TimeCountTool().getTimecounter().getCountDownMap().put(comvo.getCom_id(),comvo);
			//System.out.println("輸入物" + comvo.getCom_sts());
			// 拿到代購案件物件
			Gson gson2=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			MemberVO memvo = gson2.fromJson(req.getParameter("memVO"), MemberVO.class);
			ComPantLsVO compantsvo = comSvc.insertCom(comvo, memvo);
			System.out.println("123"+comvo.getMem_id());
			new TimeWs().sendInfo(comvo.getMem_id(), "案件:"+comvo.getCom_tit()+"有人加入競標囉");
			// outStr = gson.toJson(compantsvo);
			outStr = gson.toJson(comvo);
			System.out.println("輸出物" + outStr);
		} else if ("updateDelete".equals(action)) {
			//刪除
			ComVO comvo = gson.fromJson(req.getParameter("comVO"), ComVO.class);
			//System.out.println("輸入物" + comvo.getCom_sts());
			// 拿到代購案件物件
			ComVO comvoAfter = comSvc.update(comvo);//刪除也是更新 只是把案件狀態改乘下架

			outStr = gson.toJson(comvoAfter);
			System.out.println("輸出物" + outStr);
		} else if ("update".equals(action)) {

			ComVO comvo = gson.fromJson(req.getParameter("comVO"), ComVO.class);
			System.out.println("輸入物" + comvo.getCom_sts());
			
			ComVO comvoAfter = comSvc.update(comvo);

			outStr = gson.toJson(comvoAfter);
			//System.out.println("輸出物" + outStr);
		} 
		else if ("updatePic".equals(action)) {
			//System.out.println("輸入指令"+action);

			ComVO comvo = gson.fromJson(req.getParameter("comVO"), ComVO.class);
			//System.out.println("輸入物" + comvo.getCom_sts());
			// 拿到代購案件物件
			ComVO comvoAfter = comSvc.updatePic(comvo);

			outStr = gson.toJson(comvoAfter);
			//System.out.println("輸出物" + outStr);
		}else if ("getMyCase".equals(action)) {
			// Id
			String memId = req.getParameter("memId");
			// 取得會員發起的案件
			List<ComVO> comList = comSvc.selectByMemID(memId);
			outStr = gson.toJson(comList);
			//System.out.println("輸出物" + outStr);
		}else if ("getMyPart".equals(action)) {
			// Id查看會員所有案件
			String memId = req.getParameter("memId");
			 System.out.println("1Memid輸入" + String.valueOf(memId));
			ComPantLsService comPantsSvc = new ComPantLsService();
			List<ComPantLsVO> comPantList=comPantsSvc.selectAll(memId);
			 ComPantLsVO comPants=comPantList.get(0);
			 System.out.println("2輸出物" + comPants.getCom_id());
			List<ComVO> comList=comSvc.selectJoinGroupFromComPantsLs(comPantList);
			
		
			outStr = gson.toJson(comList);
			//System.out.println("3輸出物" + outStr);
		}
		else if ("selectPic".equals(action)) {
			// Id查看會員所有案件
			String comId = req.getParameter("comId");
			com.com.model.ComService comSv   =new com.com.model.ComService();		
			com.com.model.ComVO comvo=comSv.selectpic(comId);
			outStr = gson.toJson(comvo);
			System.out.println("selectPic輸出" + outStr);
		}


		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		//System.out.println("4輸出物" + outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
