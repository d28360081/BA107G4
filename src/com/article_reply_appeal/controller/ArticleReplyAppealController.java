package com.article_reply_appeal.controller;

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
import com.article_reply_appeal.model.*;
import com.articlereply.*;
import com.articlereply.model.*;
import com.emp.model.EmpVO;
import com.member.model.*;

import StoreOrderController.MailService;


public class ArticleReplyAppealController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("content-type","text/html;charset=UTF-8");   //解決json 中文回傳亂碼
		System.out.println("-------------成功進入<文章回復檢舉>控制器-------------");
		HttpSession session = req.getSession();
//		EmpVO empVO = (EmpVO) session.getAttribute("EmpVO");
		//********************************Add 文章回覆檢舉*********************************//	
		if("appeal_ReplyArticle".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("點擊檢舉回覆文章");
			try{	
				String art_re_apl_cnt =req.getParameter("art_re_apl_cnt");
				String re_id =req.getParameter("re_id");
				String mem_id =req.getParameter("mem_id");
				
				if(art_re_apl_cnt == null || art_re_apl_cnt.trim().length() == 0){
					errorMsgs.add("檢舉此篇回覆文章，此處不得留白，請說明檢舉理由");
				}
				
				ArticleReplyAppealVO araVO = new ArticleReplyAppealVO();
				araVO.setArt_re_apl_cnt(art_re_apl_cnt);
				araVO.setRe_id(re_id);
				araVO.setMem_id(mem_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("araVO", araVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				ArticleReplyAppealService araSvc = new ArticleReplyAppealService();
				araVO = araSvc.addArp(re_id, mem_id,art_re_apl_cnt);
				System.out.println("會員: " +mem_id+"  檢舉了"+re_id+"  內容為: "+art_re_apl_cnt);
				
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
			ArticleReplyAppealService araSvc = new ArticleReplyAppealService();
			String selected = req.getParameter("selected");
			System.out.println("first ajax pass");
			
			List<ArticleReplyAppealVO> aralist = null;
			if("顯示全部".equals(selected)){
				aralist = araSvc.getAll();
				
				
			}else{
				aralist = araSvc.getSelected(selected);
			}
			ArticleReplyAppealListVO araLVO = new ArticleReplyAppealListVO();
			araLVO.setAraLVO(aralist);
			
			
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(araLVO);
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
			String selected = req.getParameter("art_capl_id");
			System.out.println(selected);
			
			ArticleReplyAppealService araSvc = new ArticleReplyAppealService();
			ArticleReplyAppealVO araVO = new ArticleReplyAppealVO();
			araVO = araSvc.findByPk(selected);
			
			System.out.println(araVO.getRe_id());
			System.out.println("second ajax pass");
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(araVO);
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
			
			String art_re_apl_sts =req.getParameter("art_re_apl_sts");
			String art_re_rapl =req.getParameter("art_re_rapl");
			String art_capl_id =req.getParameter("art_capl_id");
			String re_id = req.getParameter("re_id");
//			String emp_no = empVO.getEmp_no();
			String emp_no = "EMP006";
			System.out.println("回覆文長的檢舉狀態結果為: "+art_re_apl_sts+" 檢舉回復的內容為: "+art_re_rapl+" 回文的編號為 :"+re_id+" 處理的員工為: "+emp_no);
			if(art_re_rapl == null || art_re_rapl.trim().length() == 0){
				errorMsgs.add("要評定檢舉的內容不可為空白");
			}
			
			//***************************取得撂馬仔會員***********************//
			ArticleReplyAppealService araSvc = new ArticleReplyAppealService();
			String mem_id_do = araSvc.findByPk(art_capl_id).getMem_id();
			//***************************取得被檢舉會員************************//
			  //尚未建立service 暫時使用
			ArtreDAO aDao = new ArtreDAO();
			String mem_id = aDao.findByPrimaryKey(re_id).getMem_id();
			//***************************取得會員積分*************************//
			MemService mScv = new MemService();
			Integer bonus_do =mScv.getOneMem(mem_id_do).getBonus();
			Integer bonus = mScv.getOneMem(mem_id).getBonus();
			String mem_email = mScv.getOneMem(mem_id).getMem_email();
			String mem_do_email = mScv.getOneMem(mem_id_do).getMem_email();
			//*************************************************************//
			String re_sts;
			
			ArtreVO arVO = new ArtreVO();
			
			if(art_re_apl_sts.equals("有效檢舉")){
				
				mScv.updateBonus(mem_id, bonus-50);
				mScv.updateBonus(mem_id_do, bonus_do+50);
				re_sts ="下架";
				arVO.setRe_id(re_id);
				arVO.setRe_sts(re_sts);
				aDao.updatests(arVO);
				
			}else{
				mScv.updateBonus(mem_id, bonus-20);
			}
			MailService ms = new MailService();
			ms.sendMail(mem_email, "檢舉編號: "+art_capl_id+" 判決通知",art_re_rapl);
			ms.sendMail(mem_do_email, "檢舉編號: "+art_capl_id+" 判決通知",art_re_rapl);
			
			
			
			araSvc.update(emp_no, art_re_apl_sts, art_re_rapl, art_capl_id);
			
			
			
			
		}
		    //*******************************查看原始文件 連結********************************//
		if("getOrigin".equals(action)){
			System.out.println("點選了查看原文");
			String art_capl_id = req.getParameter("art_capl_id");
			System.out.println(art_capl_id);
			//************************取得原始回文******************************//
			ArticleReplyAppealService araSvc = new ArticleReplyAppealService();
			String re_id = araSvc.findByPk(art_capl_id).getRe_id();
			System.out.println(re_id);
			ArtreDAO arSvc =new ArtreDAO();
			ArtreVO artreVO = arSvc.findByPrimaryKey(re_id);  //因為在ArtreVO 的fBP當中是以sts為正常才查得到，如果下架會空值
			ArticleService aSvc = new ArticleService();
			String art_id = artreVO.getArt_id();
			System.out.println(art_id);
			ArticleVO aVO=aSvc.findByArt_id(art_id);
			System.out.println("回傳文章VO以及回文VO至前端");
			req.setAttribute("aVO", aVO);
			req.setAttribute("artreVO", artreVO);
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/AR_AppealGetOriginalFile.jsp"); 
			successView.forward(req, res);
		}
	}
}
