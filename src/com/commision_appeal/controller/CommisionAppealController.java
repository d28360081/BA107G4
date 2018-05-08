package com.commision_appeal.controller;

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

import com.com.model.ComService;
import com.com.model.ComVO;
import com.commision_appeal.model.CommisionAppealListVO;
import com.commision_appeal.model.CommisionAppealService;
import com.commision_appeal.model.CommisionAppealVO;
import com.emp.model.EmpVO;
import com.member.model.MemService;

import StoreOrderController.MailService;


public class CommisionAppealController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("content-type","text/html;charset=UTF-8");   //解決json 中文回傳亂碼
		System.out.println("-------------成功進入<代購檢舉>控制器-------------");
		HttpSession session = req.getSession();
//		EmpVO empVO = (EmpVO) session.getAttribute("EmpVO");
		//********************************新增檢舉項目*********************************//	
		if("appeal_This_Commision".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("點擊檢舉代購");	
			
			try{
				String mem_id = req.getParameter("mem_id");
				String com_id = req.getParameter("com_id");
				String apl_cnt = req.getParameter("apl_cnt");
				if(apl_cnt == null || apl_cnt.trim().length() == 0){
					errorMsgs.add("檢舉此篇內容理由不得留白");
				}
				CommisionAppealVO caVO = new CommisionAppealVO();
				caVO.setMem_id(mem_id);
				caVO.setCom_id(com_id);
				caVO.setApl_cnt(apl_cnt);
				
				CommisionAppealService caSvc = new CommisionAppealService();
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("caVO", caVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				caSvc.add(mem_id, com_id, apl_cnt);
				System.out.println("會員: " +mem_id+"  檢舉了"+com_id+"  內容為: "+apl_cnt);
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
				successView.forward(req, res);	
			}
			catch(Exception e){
				
			}
		}
		
		if("selected".equals(action)){
			CommisionAppealService caSvc = new CommisionAppealService();
			String selected = req.getParameter("selected");
			System.out.println("first ajax pass" + "選擇的狀態列為 : "+selected);
			
			List<CommisionAppealVO> calist = null;
			if("顯示全部".equals(selected)){
				calist = caSvc.getAll();
			}else{
				calist = caSvc.getSelected(selected);
			}
			CommisionAppealListVO caLVO = new CommisionAppealListVO();
			caLVO.setCaLVO(calist);;
			JSONObject jsonObject = new JSONObject(caLVO);
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
			String com_apl_id = req.getParameter("com_apl_id");
			System.out.println(com_apl_id);
			
			CommisionAppealService caSvc = new CommisionAppealService();
			CommisionAppealVO caVO = new CommisionAppealVO(); 
			caVO = caSvc.findByPk(com_apl_id);
			
			System.out.println(caVO.getCom_id());
			System.out.println("second ajax pass");
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(caVO);
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
			String apl_sts =req.getParameter("apl_sts");
			String com_rapl =req.getParameter("com_rapl");
			String com_apl_id =req.getParameter("com_apl_id");
//			String emp_no = empVO.getEmp_no();
			String emp_no = "EMP006";
			System.out.println("後端工作人員: "+emp_no+" 點選了處理檢舉代購");
			
			//***************************取得資料驗證**************************//
			if(com_rapl == null || com_rapl.trim().length() == 0){
				errorMsgs.add("檢舉此篇文章，此處不得留白，請說明檢舉理由");
			}
			//***************************取得撂馬仔會員************************//
			CommisionAppealService caSvc = new CommisionAppealService();
			String mem_id_do =caSvc.findByPk(com_apl_id).getMem_id();
			//***************************取得被檢舉會員************************//
			ComService cSvc = new ComService();
			String com_id = caSvc.findByPk(com_apl_id).getCom_id();
			String mem_id = cSvc.select(com_id).getMem_id();
			//***************************取得會員積分*************************//
			MemService mScv = new MemService();
			Integer bonus_do = mScv.getOneMem(mem_id_do).getBonus();
			Integer bonus = mScv.getOneMem(mem_id).getBonus();
			//*************************************************************//
			String com_it_sts = cSvc.select(com_id).getCom_it_sts();
			String mem_email = mScv.getOneMem(mem_id).getMem_email();
			String mem_do_email = mScv.getOneMem(mem_id_do).getMem_email();
			
			if(apl_sts.equals("有效檢舉")){
				bonus -=50;
				bonus_do +=50;
				com_it_sts="下架";
			}else{
				bonus_do -=20;
				com_it_sts="正常";
			}
			MailService ms = new MailService();
			ms.sendMail(mem_email, "檢舉編號: "+com_apl_id+" 判決通知",com_rapl);
			ms.sendMail(mem_do_email, "檢舉編號: "+com_apl_id+" 判決通知",com_rapl);
			
			
			ComVO comVO = new ComVO();
			comVO.setCom_id(com_id);
			comVO.setCom_it_sts(com_it_sts);
			
			mScv.updateBonus(mem_id, bonus);
			mScv.updateBonus(mem_id_do, bonus_do);
			caSvc.update(emp_no, apl_sts, com_rapl, com_apl_id);
			cSvc.update(comVO);
			System.out.println("代購檢舉處理成功");
		}
		 //*******************************查看原始文件 連結********************************//
		if("getOrigin".equals(action)){
			System.out.println("點選了查看原文");
			String com_apl_id = req.getParameter("com_apl_id");
			System.out.println(com_apl_id);
			//************************取得原始回文******************************//
			CommisionAppealService caSvc = new CommisionAppealService();
			String com_id = caSvc.findByPk(com_apl_id).getCom_id();
			ComService cSvc = new ComService();
			ComVO cVO = cSvc.select(com_id);
			System.out.println("回傳代購VO至前端");
			req.setAttribute("cVO", cVO);
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/C_AppealGetOriginalFile.jsp"); 
			successView.forward(req, res);
			
			
			
		}
	}
	
}
