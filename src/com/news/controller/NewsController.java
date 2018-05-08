package com.news.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;
import com.news.model.NewsService;
import com.news.model.NewsVO;

public class NewsController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("content-type", "text/html;charset=UTF-8"); // 解決json
																	// 中文回傳亂碼
		System.out.println("-------------成功進入<新聞>控制器-------------");
		HttpSession session = req.getSession();
//		EmpVO empVO = (EmpVO) session.getAttribute("EmpVO");

		// ********************************變更發布*********************************//
		if ("released".equals(action)) {
			System.out.println("進入選擇狀態ok");

			NewsService nsSvc = new NewsService();
			String ns_id = new String(req.getParameter("ns_id"));
			String ns_sts = new String(req.getParameter("seleased"));

			nsSvc.updatests(ns_id, ns_sts);
			System.out.println("選擇的"+ns_id+"修改的狀態為: "+ns_sts);
		}
		// ********************************變更發布*********************************//
		if ("newseditor".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("進入判斷 action =" + action + "等候控制器。。。。");
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			try {
				String ns_tit = new String(req.getParameter("ns_tit").getBytes("ISO-8859-1"), "UTF-8");
				String ns_cnt = new String(req.getParameter("ns_cnt").getBytes("ISO-8859-1"), "UTF-8");
				String ns_cdate = new String(req.getParameter("ns_cdate").getBytes("ISO-8859-1"), "UTF-8");
//				String emp_no = empVO.getEmp_no();
				String emp_no = "EMP006";
				System.out.println("選定發布時間為" + ns_cdate);
				if (ns_tit == null || ns_tit.trim().length() == 0) {
					errorMsgs.add("新增文章標題不得為空白");
				}
				if (ns_cnt == null || ns_cnt.trim().length() == 0) {
					errorMsgs.add("新增文章內容不得為空白");
				}
				if (ns_cdate == null || ns_cdate.trim().length() == 0) {
					errorMsgs.add("請選擇您的消息發布時間，不得為空白");
				}

				// ********************************街道資料送入DB********************************//
				NewsService nsSvc = new NewsService();
				NewsVO nsVO = new NewsVO();
				nsVO.setNs_tit(ns_tit);
				nsVO.setNs_cnt(ns_cnt);
				nsVO.setNs_cdate(ns_cdate);
				String pk = nsSvc.add(emp_no, ns_tit, ns_cnt, ns_cdate);

				// ********************************進行排程處理*********************************//
				java.text.SimpleDateFormat dateFormate = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				dateFormate.applyPattern("yyyy-MM-dd HH:mm:ss");
				java.util.Date workingtime = dateFormate.parse(ns_cdate);
				Timer timer = new Timer();
				TimerTask showtime = new TimerTask() {
					public void run() {
						String ns_id = pk;
						nsSvc.updatests(ns_id, "發布"); // 怎麼當下抓自增主鍵ID
						System.out.println("ns_id = " + ns_id);
						System.out.println("發布工作完成");
					}
				};
				timer.schedule(showtime, workingtime);
				System.out.println("進入排程，等候工作" + new Date());

				res.sendRedirect(req.getContextPath() + "/appeal/NewsManagement.jsp");
				// ********************************進行排程處理*********************************//
				// RequestDispatcher successView = req.getRequestDispatcher();
				// // 修改成功後,轉交回送出修改的來源網頁
				// successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/appeal/NewsEditor.jsp");
				failureView.forward(req, res);
			}

		}

		if ("tonsupdate".equals(action)) {
			String ns_id = req.getParameter("ns_id");
			System.out.println("進入消息修改頁面");
			NewsService nsSvc =new NewsService();
			NewsVO nsVO = nsSvc.findByPk(ns_id);
			System.out.println("要修改的新聞標題為 : " + nsVO.getNs_tit());
			req.setAttribute("nsVO", nsVO);
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/NewsUpdate.jsp"); // 
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) {
			String ns_id = req.getParameter("ns_id");
			System.out.println("點擊新聞消息刪除按鈕");
			NewsService nsSvc =new NewsService();
			nsSvc.delete(ns_id);;
			System.out.println("已經將新聞編號為 : " + ns_id +"刪除");
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/NewsManagement.jsp"); // 
			successView.forward(req, res);
		}
		

		
		if("newseupdate".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("進入判斷 action =" + action + "等候控制器。。。。");
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
		
			try{
				System.out.println("點擊送出修改消息內容");
				String ns_id = req.getParameter("ns_id");
				String ns_tit = new String (req.getParameter("ns_tit").getBytes("ISO-8859-1"),"UTF-8");
				String ns_cnt = new String (req.getParameter("ns_cnt").getBytes("ISO-8859-1"),"UTF-8");
				String ns_sts = new String (req.getParameter("ns_sts").getBytes("ISO-8859-1"),"UTF-8");
				
				if (ns_tit == null || ns_tit.trim().length() == 0) {
					errorMsgs.add("修改文章標題不得為空白");
				}
				if (ns_cnt == null || ns_cnt.trim().length() == 0) {
					errorMsgs.add("修改文章標題不得為空白");
				}
				
				System.out.println("修改後的標題 : "+ns_tit+ " <修改後的內文為 : "+ ns_cnt+ " <修改後的狀態為 : " +ns_sts);
				
				NewsService nsSvc = new NewsService();
				nsSvc.update(ns_tit,ns_cnt,ns_sts,ns_id);
				System.out.println("修改成功。。。。");
				
				
				res.sendRedirect(req.getContextPath() + "/appeal/NewsManagement.jsp");
				
			}catch (Exception e) {
					errorMsgs.add("修改資料失敗:" + e.getMessage());
					res.sendRedirect(req.getContextPath() + requestURL);
				
			}
				
		}
		if("shownews".equals(action)){
			System.out.println("點選了shownews");
			String ns_id =req.getParameter("ns_id");
			System.out.println("選擇的新聞是: "+ns_id);
			NewsService ns = new NewsService();
			NewsVO nsVO = ns.findByPk(ns_id);
			System.out.println(nsVO.getNs_cnt());
			
			req.setAttribute("nsVO", nsVO);
			RequestDispatcher successView = req.getRequestDispatcher("/frontform/NewsShow.jsp"); 
			successView.forward(req, res);
		}
		
		
		
		
		
		
		
	}

}
