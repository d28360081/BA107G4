package android.com.comt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.com.model.ComService;
import android.com.com.model.ComVO;
import android.com.comt.model.ComComtVO;
import android.com.comt.model.ComtService;
import android.com.member.model.MemberVO;

public class ComComtServlet extends HttpServlet{
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String outStr = "";
		ComtService comtSvc=new ComtService();

		Gson gson = new Gson();
		String action = req.getParameter("action");
		System.out.println(action);
		
		//動作比對
		if ("getAll".equals(action)) {
			//撈此案件的留言
			String comid = req.getParameter("comid");

			List<ComComtVO> comtList=comtSvc.selectAll(comid);
			outStr = gson.toJson(comtList);

		}else if("insert".equals(action)){
			//輸入留言
			ComComtVO comcomtvo = gson.fromJson(req.getParameter("comComt"),
					ComComtVO.class);
			 ComComtVO comComt = comtSvc.insert(comcomtvo);
			 outStr = comComt == null ? "" : gson.toJson(comComt);
	         System.out.println("輸回去安著的留言物件"+outStr);
		}
		
		res.setContentType(contentType);
		PrintWriter out=res.getWriter();
		System.out.println("輸出物"+outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
