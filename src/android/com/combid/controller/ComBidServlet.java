package android.com.combid.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.google.gson.Gson;

import android.com.combid.model.ComBidService;
import android.com.combid.model.ComBidVO;
import android.com.member.model.MemberService;
import android.com.member.model.MemberVO;
import timeCount.TimeWs;

public class ComBidServlet extends HttpServlet{
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	

		ComBidService comBidSvc=new ComBidService();

		Gson gson = new Gson();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println("參數指令是：" + action);

		/** 比對動作 **/
		if ("isAlreadyIn".equals(action)) {
			
			String memId = req.getParameter("memId");
			String comId = req.getParameter("comId");
			outStr = String.valueOf(comBidSvc.isAlreadyIn(memId, comId));
			System.out.println("輸出物"+outStr);

		}else if("insert".equals(action)) {
			ComBidVO comBid = gson.fromJson(req.getParameter("comBid"),
					ComBidVO.class);
			outStr = String.valueOf(comBidSvc.insert(comBid));
			ComService comservice=new ComService();
			ComVO cv=comservice.select(comBid.getCom_id());
			System.out.println("123"+comBid.getMem_id());
			new TimeWs().sendInfo(cv.getMem_id(), "案件:"+cv.getCom_tit()+"有人加入競標囉");
			System.out.println("輸出物"+outStr);
			
		}
		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println("結果:" + outStr);
		out.print(outStr);
		out.close();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
