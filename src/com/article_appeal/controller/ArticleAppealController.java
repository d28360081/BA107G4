package com.article_appeal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_appeal.model.*;
import com.emp.model.EmpVO;
import com.member.model.MemService;

import StoreOrderController.MailService;


public class ArticleAppealController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("content-type","text/html;charset=UTF-8");   //解決json 中文回傳亂碼
		System.out.println("-------------成功進入<文章檢舉>控制器-------------");
		HttpSession session = req.getSession();
//		EmpVO empVO = (EmpVO) session.getAttribute("EmpVO");
		//********************************Add 文章回覆檢舉*********************************//	
		if("appeal_This_Article".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("點擊檢舉文章");
			try{	
				String art_apl_cnt =req.getParameter("art_apl_cnt");
				String art_id =req.getParameter("art_id");
				String mem_id =req.getParameter("mem_id");
				
				if(art_apl_cnt == null || art_apl_cnt.trim().length() == 0){
					errorMsgs.add("檢舉此篇回覆文章，此處不得留白，請說明檢舉理由");
				}
				
				ArticleAppealVO aaVO = new ArticleAppealVO();
				aaVO.setArt_apl_cnt(art_apl_cnt);
				aaVO.setArt_id(art_id);
				aaVO.setMem_id(mem_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("aaVO", aaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				ArticleAppealService aaSvc = new ArticleAppealService();
				aaVO = aaSvc.addAap(art_id, mem_id,art_apl_cnt);
				System.out.println("會員: " +mem_id+"  檢舉了"+art_id+"  內容為: "+art_apl_cnt);
				
					
			}
			  catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
			  }
			}

		//********************************Option 選單*********************************//
		if("selected".equals(action)){
			ArticleAppealService aaSvc = new ArticleAppealService();
			String selected = req.getParameter("selected");
			System.out.println("first ajax pass");
			
			List<ArticleAppealVO> aalist = null;
			if("顯示全部".equals(selected)){
				aalist = aaSvc.getAll();
				
				
			}else{
				aalist = aaSvc.getSelected(selected);
			}
			ArticleAppealListVO aaLVO = new ArticleAppealListVO();
			aaLVO.setAaVOList(aalist);
			System.out.println("選擇了: "+selected);
			
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(aaLVO);
			PrintWriter out = res.getWriter();
			out.println(jsonObject);
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			
			
		}
		   //*******************************編輯按鈕 選單********************************//
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("enter second ajax");
			String selected = req.getParameter("art_apl_id");
			System.out.println(selected);
			
			ArticleAppealService aaSvc = new ArticleAppealService();
			ArticleAppealVO aaVO = new ArticleAppealVO();
			aaVO = aaSvc.findByPK(selected);
			
			System.out.println(aaVO.getArt_id());
			System.out.println("second ajax pass");
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(aaVO);
			PrintWriter out = res.getWriter();
			out.println(jsonObject);
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
		}
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			//***************************後端取得***************************//
			
			String art_apl_sts =req.getParameter("art_apl_sts");
			String art_rapl =req.getParameter("art_rapl");
			String art_apl_id =req.getParameter("art_apl_id");
			String art_id = req.getParameter("art_id");
//			String emp_no = empVO.getEmp_no();
			String emp_no = "EMP006";
			System.out.println("文章的檢舉狀態結果為: "+art_apl_sts+" 檢舉回復的內容為: "+art_rapl+" 文章的編號為 :"+art_id+" 處理的員工為: "+emp_no);
			if(art_rapl == null || art_rapl.trim().length() == 0){
				errorMsgs.add("要評定檢舉的內容不可為空白");
			}
			
			//***************************取得撂馬仔會員***********************//
			ArticleAppealService aaSvc = new ArticleAppealService();
			String mem_id_do = aaSvc.findByPK(art_apl_id).getMem_id();
			//***************************取得被檢舉會員************************//
			ArticleService aScv =new ArticleService();
			String mem_id = aScv.findByArt_id(art_id).getMem_id();
			//***************************取得會員積分*************************//
			MemService mScv = new MemService();
			Integer bonus_do =mScv.getOneMem(mem_id_do).getBonus();
			Integer bonus = mScv.getOneMem(mem_id).getBonus();
			String mem_email = mScv.getOneMem(mem_id).getMem_email();
			String mem_do_email = mScv.getOneMem(mem_id_do).getMem_email();
			//*************************************************************//
			String art_sts;
			
			ArticleVO aVO = new ArticleVO();
			
			if(art_apl_sts.equals("有效檢舉")){
				
				mScv.updateBonus(mem_id, bonus-50);
				mScv.updateBonus(mem_id_do, bonus_do+50);
				art_sts ="下架";
				aScv.updatereSts(art_id, art_sts);
				
			}else{
				mScv.updateBonus(mem_id, bonus-20);
			}
			MailService ms = new MailService();
			ms.sendMail(mem_email, "檢舉編號: "+art_apl_id+" 判決通知",art_rapl);
			ms.sendMail(mem_do_email, "檢舉編號: "+art_apl_id+" 判決通知",art_rapl);
			aaSvc.update(emp_no, art_apl_sts, art_rapl, art_apl_id);
			
			
			
			
		}
		    //*******************************查看原始文件 連結********************************//
		if("getOrigin".equals(action)){
			System.out.println("點選了查看原文");
			String art_apl_id = req.getParameter("art_apl_id");
			System.out.println(art_apl_id);
 //************************取得原始回文******************************//
			ArticleAppealService aaSvc = new ArticleAppealService();
			String art_id = aaSvc.findByPK(art_apl_id).getArt_id();
			System.out.println(art_id);
			ArticleService aSvc = new ArticleService();
			ArticleVO aVO = aSvc.findByArt_id(art_id);
			System.out.println(aVO.getArt_tlt()+" 回傳文章VO至前端");
			req.setAttribute("aVO", aVO);
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/A_AppealGetOriginalFile.jsp"); 
			successView.forward(req, res);
		}
	}
}
