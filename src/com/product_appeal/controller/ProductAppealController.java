package com.product_appeal.controller;

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

import com.Product.model.ProductService;
import com.Product.model.ProductVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_appeal.model.ArticleAppealService;
import com.emp.model.EmpVO;
import com.member.model.MemService;
import com.member.model.MemVO;
import com.product_appeal.model.ProductAppealListVO;
import com.product_appeal.model.ProductAppealService;
import com.product_appeal.model.ProductAppealVO;

import StoreOrderController.MailService;

public class ProductAppealController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setHeader("content-type","text/html;charset=UTF-8");   //解決json 中文回傳亂碼
		System.out.println("-------------成功進入<商品檢舉>控制器-------------");
		HttpSession session = req.getSession();
//		EmpVO empVO = (EmpVO) session.getAttribute("EmpVO");
		//********************************點擊商品檢舉按鈕*********************************//	
		if("appeal_This_Product".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the req scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL");
				System.out.println("點擊檢舉商品");
			try{
				ProductAppealService paSvc = new ProductAppealService();
				String mem_id = req.getParameter("mem_id");
				String it_id = req.getParameter("it_id");
				String it_apl_cnt = req.getParameter("it_apl_cnt");
				
				
				if(it_apl_cnt == null || it_apl_cnt.trim().length() == 0){
					errorMsgs.add("檢舉此項商品原因，請說明檢舉理由");
				}
				paSvc.add(mem_id, it_id, it_apl_cnt);
				System.out.println("會員: " +mem_id+"  檢舉了"+it_id+"  內容為: "+it_apl_cnt);
				}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				}
		}
		
		//********************************Option 選單*********************************//	
		if("selected".equals(action)){
			ProductAppealService paSvc = new ProductAppealService();
			String selected = req.getParameter("selected");
			System.out.println("first ajax pass" + "選擇的狀態列為 : "+selected);
			
			List<ProductAppealVO> palist = null;
			if("顯示全部".equals(selected)){
				palist = paSvc.getAll();
				
				
			}else{
				palist = paSvc.getSelected(selected);
			}
			ProductAppealListVO paLVO = new ProductAppealListVO();
			paLVO.setPaLVO(palist);;
			
			
			
			
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(paLVO);
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
			String it_apl_id = req.getParameter("it_apl_id");
			System.out.println(it_apl_id);
			
			ProductAppealService paSvc = new ProductAppealService();
			ProductAppealVO paVO = new ProductAppealVO(); 
			paVO = paSvc.findByPk(it_apl_id);
			
			System.out.println(paVO.getIt_id());
			System.out.println("second ajax pass");
			//********************將資料庫撈到的資料包成JSON，再傳回前端***********************//
			JSONObject jsonObject = new JSONObject(paVO);
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
			String it_rapl =req.getParameter("it_rapl");
			String it_apl_id =req.getParameter("it_apl_id");
//			String emp_no = empVO.getEmp_no();
			String emp_no ="EMP006";
			//***************************取得撂馬仔會員***********************//
			ProductAppealService paSvc = new ProductAppealService();
			String mem_id = paSvc.findByPk(it_apl_id).getMem_id();
			//***************************取得被檢舉會員************************//
			MemService mSvc = new MemService();
			Integer bonus = mSvc.getOneMem(mem_id).getBonus();
			String mem_email = mSvc.getOneMem(mem_id).getMem_email();
			
			
			String it_id = paSvc.findByPk(it_apl_id).getIt_id();
			String it_sts;
			ProductVO pVO = new ProductVO();
			ProductService pSvc = new ProductService();
			if(apl_sts.equals("有效檢舉")){
				//給予檢舉商店商品會員檢舉獎勵
				mSvc.updateBonus(mem_id, bonus+50);
				it_sts="下架";
				pVO.setIt_sts(it_sts);
				pVO.setIt_id(it_id);
				pSvc.update_STS(pVO);
			}else{
				mSvc.updateBonus(mem_id, bonus-20);
			}
			MailService ms = new MailService();
			ms.sendMail(mem_email, "檢舉編號: "+it_apl_id+" 判決通知",it_rapl);
			paSvc.update(emp_no, apl_sts, it_rapl, it_apl_id);
			
			
			
			
		}
		//*******************************查看原始文件 連結********************************//
		if("getOrigin".equals(action)){
				System.out.println("點選了查看原文");
				String it_apl_id = req.getParameter("it_apl_id");
				System.out.println(it_apl_id);
	 //************************取得原始回文******************************//
			ProductAppealService paSvc = new ProductAppealService();
			String it_id = paSvc.findByPk(it_apl_id).getIt_id();
			System.out.println(it_id);
			ProductService pSvc = new ProductService();
			ProductVO pVO = pSvc.findProduct(it_id);
			System.out.println(pVO.getIt_name()+" 回傳文章VO至前端");
			req.setAttribute("pVO", pVO);
			RequestDispatcher successView = req.getRequestDispatcher("/appeal/P_AppealGetOriginalFile.jsp"); 
			successView.forward(req, res);
		}
	}
}

