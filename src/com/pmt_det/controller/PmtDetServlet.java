package com.pmt_det.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pmt.model.PmtService;
import com.pmt.model.PmtVO;
import com.pmt_det.model.PmtDetService;
import com.pmt_det.model.PmtDetVO;

public class PmtDetServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res)
		throws ServletException , IOException  ,RuntimeException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if(req.getSession().getAttribute("PmtVO")!=null){
				List<String> errorMsgs = new LinkedList<String>();
				errorMsgs.add("尚未登入系統");
				req.getRequestDispatcher("/pmt/.jsp").forward(req, res);
				return;
		}
	
		//新增
		if("insert".equals(action)){// 來自addPmt.jsp的請求  
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("ok");				
			try{
					String pmt_id = new String(req.getParameter("pmt_id").trim());
					String[] it_id = req.getParameterValues("it_id");
					System.out.println("促銷編號:"+ "pmt_id");
					if(it_id != null){
							for(String id:it_id){
								System.out.println("商品編號" +id);
							}
					}			
					PmtDetVO pmtdetVO = new PmtDetVO();	//開始新增資料
					PmtDetService pmtdetSvc = new PmtDetService();
					pmtdetSvc.addPmtDet(pmtdetVO);//新增完 轉交
					String url = "/com.pmt.view/.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/com.pmt.view/.jsp");
					failureView.forward(req, res);
				}
		}
//		public void insert2(PmtDetVO pmtdetVO, Connection con);
	
			
			//刪除----要改促銷明細裡刪除 所以jsp要想要怎麼做----
		if("pmtdet_delete".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();				
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL"); 
				System.out.println(requestURL);
	// 原促銷= 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
				
				try {
					/***************************1.接收請求參數***************************************/
					String pmt_id = new String(req.getParameter("pmt_id"));
					String it_id = new String(req.getParameter("it_id"));	
					System.out.println(pmt_id);
					System.out.println(it_id);
					/***************************2.開始刪除資料***************************************/
//					PmtDetService pmtdetSvc = new PmtDetService();
//					PmtDetVO pmtdetVO = pmtdetSvc.deletePmtDet(pmt_id, it_id);
//					pmtdetSvc.deletePmtDet(pmt_id, it_id);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(req.getParameter("requestURL"));
					failureView.forward(req, res);
				}
			}
		}

//	public void update(PmtDetVO pmtdetVO);//裡面的商品只能新增 刪除
//	public PmtDetVO findByPrimaryKey_PMT(String pmt_id);
//	public PmtDetVO findByPrimaryKey_IT(String it_id);
//	public List<PmtDetVO> getAll(); 

	
	
}

