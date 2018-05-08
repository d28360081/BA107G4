package android.com.comnof.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.combid.model.ComBidVO;
import android.com.comnof.model.ComNofService;
import android.com.comnof.model.ComNofVO;
import android.com.comt.model.ComComtVO;


public class ComNofServlet extends HttpServlet{
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	

		ComNofService comNofSvc=new ComNofService();

		Gson gson = new Gson();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println("參數指令是：" + action);

		/** 比對動作 **/
		if ("getHistoryNof".equals(action)) {
			//撈所有通知
			String memId = req.getParameter("memid");
			List <ComNofVO> comtNofList=comNofSvc.selectAll(memId);
			outStr = gson.toJson(comtNofList);
			
			System.out.println("輸出物"+outStr);

		}
		/** 比對動作 **/
		else if ("update".equals(action)) {
			//改變已讀未讀狀態
			ComNofVO comNofVO = gson.fromJson(req.getParameter("comnofVO"),
					ComNofVO.class);
			String str=comNofVO.getMem_id();
			String str2=comNofVO.getNof_sts();
			System.out.println("comNof Update"+str);
			System.out.println("comNof Update"+str2);
			comNofSvc.update(comNofVO);
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
