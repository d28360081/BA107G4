package com.member.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.member.model.MemDAO;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class login
 */
@WebServlet("/member/login")
@MultipartConfig
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
//		String action = request.getParameter("action");
//		if("login".equals(action)) {
//			List<String>errMsg = new LinkedList<String>();
//			request.setAttribute("errMsg",errMsg);
//			try {
//				String acc = new String(request.getParameter("acc").trim());
//				String eacc = "^[(a-zA-z0-9)] {6,10}$";
//				if(acc==null || acc.trim().length()==0) {
//					errMsg.add("請輸入帳號");
//				}else if(! acc.trim().matches(eacc)) {
//					errMsg.add("帳號為英文大小寫，且於6~10位元間");
//				}
//				String psw = new String(request.getParameter("psw").trim());
//				if(psw==null || psw.trim().length()==0) {
//					errMsg.add("請輸入密碼");
//				}
//				
//				MemVO memVO = new MemVO();
//				memVO.setAcc(acc);
//				memVO.setPsw(psw);
//				
//				if(!errMsg.isEmpty()) {
//					RequestDispatcher fail = request.getRequestDispatcher("/member/Indexnologin.jsp");
//					fail.forward(request, response);
//					return;
//				}
//				MemService memSvc = new MemService();
//				String authuser = memSvc.authuser(acc, psw);
//				
//				if(authuser.equals("action")) {
//					HttpSession session = request.getSession();
//					session.setAttribute("memVO", memVO);
//					try {
//						String location = (String) session.getAttribute("location");
//						if (location != null) {
//							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//							response.sendRedirect(location);
//							return;
//						}
//					} catch (Exception ignored) {}
//					response.sendRedirect(request.getContextPath() + "/member/memberIndex.html");
//				}else {
//					request.setAttribute("errMsg", authuser);
//					response.sendRedirect(request.getContextPath()+"/member/Indexnologin.jsp");
//				}
//				
//				}catch(Exception e) {
//				errMsg.add("登入失敗"+e.getMessage());
//				RequestDispatcher fail = request.getRequestDispatcher("/member/Indexnologin.jsp");
//				fail.forward(request, response);
//			}
//		}
		
		
		
		
		
		/////login request start
		String action = request.getParameter("action");

		if("login".equals(action)) {
		try{
		String acc = request.getParameter("acc");
		String psw = request.getParameter("psw");
		
		List<String>errMsg = new LinkedList<String>();
		request.setAttribute("errMsg",errMsg);
		
		
		MemService memSvc = new MemService();
		
		String authuser = memSvc.authuser(acc, psw);
		
		if("action".equals(authuser)) {
			MemVO memVO = memSvc.getByAcc(acc);
			HttpSession session = request.getSession();
			session.setAttribute("MemVO", memVO); 

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					response.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			response.sendRedirect(request.getContextPath() + "/member/memberIndex2.jsp");
			}else if("empty".equals(authuser)) {
				errMsg.add("請輸入帳號、密碼"+"(･ω´･ )");
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
			}else{
			errMsg.add("帳號錯誤，請重新輸入");
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
			}
			}catch(Exception e){}
		}
//		}else if("accempty".equals(authuser)){
//			errMsg.add("請輸入帳號");
//			request.setAttribute("errMsg", errMsg);
//			request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
//		}else if("pswempty".equals(authuser)) {
//			errMsg.add("請輸入密碼");
//			request.setAttribute("errMsg", errMsg);
//			request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
//		}else if("accrightpswwrong".equals(authuser)) {
//			errMsg.add("密碼錯誤，請重新輸入");
//			request.setAttribute("errMsg", errMsg);
//			request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
//		}else if("allwrong".equals(authuser)) {
//			errMsg.add("帳號不存在，請重新輸入");
//			request.setAttribute("errMsg", errMsg);
//			request.getRequestDispatcher("/member/Indexnologin.jsp").forward(request, response);
//		}
		////login end
		
		////update start
		
		if("goupdate".equals(action)){
			String requestURL = request.getParameter("requestURL");
			try{
				String mem_id = request.getParameter("mem_id");
				MemService memSrc = new MemService();
				MemVO memVO = memSrc.getOneMem(mem_id);
				request.setAttribute("MemVO", memVO);
				String url = "/member/memberIndex_updatedata.jsp";
				RequestDispatcher success = request.getRequestDispatcher(url);
				success.forward(request, response);
			}catch(Exception e){
				RequestDispatcher fail = request.getRequestDispatcher(requestURL);
				fail.forward(request, response);
			}
		}
		
		if("update".equals(action)){
			List<String>errMsgs = new LinkedList<String>();
			request.setAttribute("errMsgs",errMsgs);
			String requestURL = request.getParameter("requestURL");
			try{
				String mem_id = request.getParameter("mem_id");
				String acc = request.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if(acc==null || acc.trim().length()==0){
					errMsgs.add("請輸入新帳號");
				}else if(!acc.trim().matches(accReg)){
					errMsgs.add("帳號需再5~10字元間，且為英文數字混合");
				}
				String psw = request.getParameter("psw");
				if(psw==null || psw.trim().length()==0){
					errMsgs.add("請輸入密碼");
				}
				String cpsw = request.getParameter("cpsw");
				if(!cpsw.equals(psw)){
					errMsgs.add("確認密碼錯誤");
				}else if(cpsw==null || cpsw.trim().length()==0){
					errMsgs.add("請輸入確認密碼");
				}
				String mem_name = request.getParameter("mem_name");
				if(mem_name==null || mem_name.trim().length()==0){
					errMsgs.add("請輸入暱稱");
				}
				java.sql.Date bir_dt=null;
				bir_dt=java.sql.Date.valueOf(request.getParameter("bir_dt").trim());
				
				String address = request.getParameter("address");
				if(address==null || address.trim().length()==0){
					errMsgs.add("請輸入地址");
				}
				String mem_pay = request.getParameter("mem_pay");
				String mem_email = request.getParameter("mem_email");
				Part photo = request.getPart("mem_photo");
				byte[] mem_photo = null;
				MemService memSrc = new MemService();
					if (photo.getInputStream().available()>0) {
						System.out.println("bbbbb");
						InputStream in = photo.getInputStream();
						mem_photo = new byte[in.available()];
						in.read(mem_photo);
						in.close();
					}
					else if(photo.getInputStream().available()==0){
//						byte[] copy = request.getParameter("mem_photo2").getBytes();
//						System.out.println("aaaaaaaaaaaaaaaaaaaaa");
//						InputStream in = new ByteArrayInputStream(copy);
						
//						in.read(mem_photo);
//						in.close();
						mem_photo = memSrc.getOneMem(request.getParameter("mem_id")).getMem_photo();
					}
				
				
				MemVO memVO = new MemVO();
				memVO.setAcc(acc);
				memVO.setPsw(psw);
				memVO.setBir_dt(bir_dt);
				memVO.setMem_photo(mem_photo);
				memVO.setMem_email(mem_email);
				memVO.setMem_name(mem_name);
				memVO.setMem_pay(mem_pay);
				memVO.setMem_id(mem_id);
				
				if(!errMsgs.isEmpty()){
					request.setAttribute("MemVO", memVO);
					RequestDispatcher faile = request.getRequestDispatcher("/member/memberIndex_updatedata.jsp");
					faile.forward(request, response);
					return;
				}
				
				
				memVO = memSrc.updateMem(acc, psw, bir_dt, address, mem_pay, mem_photo, mem_id,mem_email,mem_name);
				memVO = memSrc.getOneMem(mem_id);
				request.setAttribute("MemVO", memVO);
				HttpSession session = request.getSession();
				session.setAttribute("MemVO", memVO);
				
				String url = "/member/memberIndex2.jsp";
				RequestDispatcher success = request.getRequestDispatcher(url);
				success.forward(request, response);

			}catch(Exception e){
				e.printStackTrace();
				errMsgs.add("修正錯誤"+e.getMessage());
				RequestDispatcher faile = request.getRequestDispatcher("/member/memberIndex_updatedata.jsp");
				faile.forward(request, response);
				return;
			}
		}
		
		if("back".equals(action)){
			try {
				HttpSession session = request.getSession();
				String location = (String) session.getAttribute("location");
				if (location != null) {
					System.out.println("zzzzzzzzzzzzzzzzzzz");
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					response.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			response.sendRedirect(request.getContextPath() + "/member/memberIndex2.jsp");
		}
		
		if("register".equals(action)){
			List<String>errMsgs = new LinkedList<String>();
			request.setAttribute("errMsgs",errMsgs);
			MemService memSrc = new MemService();
			try{
				String acc = request.getParameter("acc");
				String accReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if(acc==null || acc.trim().length()==0){
					errMsgs.add("請輸入帳號");
				}else if(memSrc.isUserIdExist(acc)==true){
					errMsgs.add("輸入帳號已存在，請重新輸入");
				}else if(!acc.trim().matches(accReg)){
					errMsgs.add("帳號需再6~10字元間，且為英文數字混合");
				}
				String psw = request.getParameter("psw");
				if(psw==null|| psw.trim().length()==0){
					errMsgs.add("請輸入密碼");
				}
				String cpsw = request.getParameter("cpsw");
				if(cpsw==null || cpsw.trim().length()==0){
					errMsgs.add("請輸入確認密碼");
				}else if(!psw.equals(cpsw)){
					errMsgs.add("確認密碼錯誤");
				}
				String mem_name = request.getParameter("mem_name");
				if(mem_name==null || mem_name.trim().length()==0){
					errMsgs.add("請輸入暱稱");
				}
				java.sql.Date bir_dt=null;
				try{
					bir_dt=java.sql.Date.valueOf(request.getParameter("bir_dt").trim());
				}catch(IllegalArgumentException e){
					bir_dt= new java.sql.Date(System.currentTimeMillis());
					errMsgs.add("請輸入生日");
				}
				String address = request.getParameter("address");
				String mem_pay = request.getParameter("mem_pay");
				String mem_email = request.getParameter("mem_email");
				Part photo = request.getPart("mem_photo");
				byte[] mem_photo = null;
				try{
					if (photo != null) {
						InputStream in = photo.getInputStream();
						mem_photo = new byte[in.available()];
						in.read(mem_photo);
						in.close();
					}
				}
				catch(NullPointerException ne){
					photo.delete();
				}
				

				MemVO memVO = new MemVO();
				memVO.setAcc(acc);
				memVO.setPsw(psw);
				memVO.setBir_dt(bir_dt);
				memVO.setMem_name(mem_name);
				memVO.setMem_photo(mem_photo);
				memVO.setMem_email(mem_email);
				memVO.setMem_pay(mem_pay);
				

				if (!errMsgs.isEmpty()) {
					request.setAttribute("MemVO", memVO);
					RequestDispatcher faile = request.getRequestDispatcher("/member/register2.jsp");
					faile.forward(request, response);
					return;
				}

				memVO = memSrc.addMem(acc, psw, bir_dt, address, mem_pay, mem_email,mem_name,mem_photo);
				memVO = memSrc.getByAcc(acc);
				HttpSession session = request.getSession();
				session.setAttribute("MemVO", memVO);

				String url = "/member/memberIndex2.jsp";
				RequestDispatcher success = request.getRequestDispatcher(url);
				success.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				errMsgs.add("修正錯誤" + e.getMessage());
				RequestDispatcher faile = request.getRequestDispatcher("/member/register2.jsp");
				faile.forward(request, response);
				return;
			}
		}
		
		if("logout".equals(action)){
			System.out.println("aaaaaaaaa");
			HttpSession session = request.getSession();
			session.invalidate(); //removes all session attributes bound to the session
			request.setAttribute("errMessage", "You have logged out successfully");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/member/Logout.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
//	public String getFileName(Part part){
//		String partHeader = part.getHeader("Content-Disposition");
//		for (String content : partHeader.split(";")) { 
//			if (content.trim().startsWith("filename")) { 
//			return content.substring(content.indexOf('=') + 1).trim().replace("\"", ""); 
//			} 
//		} 
//		return null;
//	}
}
