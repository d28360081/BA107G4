package android.com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.com.member.model.MemberDAO_interface;
import android.com.member.model.MemberService;
import android.com.member.model.MemberVO;
import android.com.member.model.MemberDAO;

public class MemberServlet extends HttpServlet {
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	

		MemberService memSvc=new MemberService();

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println("參數指令是：" + action);

		/** 比對動作 **/
		if ("isMember".equals(action)) {
			
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			outStr = String.valueOf(memSvc.isMember(userId, password));

		}else if("findByAccandPsw".equals(action)) {
			   	String account = req.getParameter("account");
	            String password = req.getParameter("password");
	            MemberVO member = memSvc.findByAccandPsw(account, password);
	            outStr = member == null ? "" : gson.toJson(member);
	            System.out.println(outStr);
			
		}else if ("update".equals(action)) {
			MemberVO member = gson.fromJson(req.getParameter("member"),
					MemberVO.class);
			outStr = String.valueOf(memSvc.update(member));
		}
		else if ("findById".equals(action)) {
			String memId = req.getParameter("memId");
			 MemberVO member =memSvc.findById(memId);
			 outStr = member == null ? "" : gson.toJson(member);
	         //System.out.println("輸出物"+outStr);
		}else if ("selectPic".equals(action)) {
			 MemberVO member =new MemberVO();
			String memId = req.getParameter("memId");
			member =memSvc.selectPic(memId);
			 outStr = member == null ? "" : gson.toJson(member);
	     
		}
		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		
		out.print(outStr);
		out.close();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
