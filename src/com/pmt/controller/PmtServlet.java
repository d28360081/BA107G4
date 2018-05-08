package com.pmt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Product.model.ProductVO;
import com.google.gson.Gson;
import com.pmt.model.PmtService;
import com.pmt.model.PmtVO;
import com.pmt.thread.PmtThread;
import com.pmt.websocket.PmtEchoServer;
import com.pmt_det.model.PmtDetService;
import com.pmt_det.model.PmtDetVO;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * @author sucla
 *
 */
@MultipartConfig
public class PmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	 
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException ,RuntimeException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");//取回的字串		
//System.out.println("pmtServlet action: " + action);
		//如果取回的不是空值(判斷會員狀態)  .getSession()→P115	.getAttribute("PmtVO")→P83
		if(req.getSession().getAttribute("PmtVO")!=null){
			List<String> errorMsgs = new LinkedList<String>();//LinkedList小吳老師課本p120
			errorMsgs.add("尚未登入系統");
			req.getRequestDispatcher("/com.pmt.view/listAllPmt.jsp").forward(req, res);
			return;
		}
		
		//查詢一個--------------------------------------
		if ("getOne_For_Display".equals(action)) {		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String keyword = req.getParameter("keyword");
				if (keyword == null || (keyword.trim()).length() == 0) {
					//errorMsgs.add("資料不可空值");
					errorMsgs.add("請填關鍵字");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/com.pmt.view/pmt_index.jsp");
//					failureView.forward(req, res);
					
				}	
				if (!errorMsgs.isEmpty()) { 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/com.pmt.view/pmt_index.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.開始查詢資料 - 判斷資料是否存在*****************************************/
				PmtService pmtSvc = new PmtService();
				List<PmtVO> pmtList = pmtSvc.getByKeyword(keyword);

				if(pmtList == null){
					errorMsgs.add("促銷編號錯誤");
				}
				if (!errorMsgs.isEmpty()) { 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/com.pmt.view/pmt_index.jsp");
					failureView.forward(req, res);
					return;
				}				

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pmtList", pmtList); 
				String url = "/com.pmt.view/listOnePmt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/com.pmt.view/pmt_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		//來自listAllPmt.jsp的請求 
		if ("getOne_For_Update".equals(action)) { 
//			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				/***************************1.接收請求參數****************************************/
				String pmt_id = new String(req.getParameter("pmt_id"));				
				/***************************2.開始查詢資料****************************************/
				PmtService pmtSvc = new PmtService();
				PmtVO pmtVO = pmtSvc.getOnePmt(pmt_id);								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pmtVO", pmtVO);         
				String url = "/com.pmt.view/update_pmt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/com.pmt.view/listAllPmt.jsp");
				failureView.forward(req, res);
			}
		}	
		
		//修改----------- 圖片如果讀取無法，可以看資料庫的限制是否空值
		if ("update".equals(action)) { // 來自update_pmt_input.jsp的請求			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//動態取出請求的參數 ,trim去除頭尾空白字元
				String pmt_id = new String(req.getParameter("pmt_id").trim());
				String emp_no = new String(req.getParameter("emp_no").trim());				
				
				String pmt_name = req.getParameter("pmt_name");
				String pmt_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (pmt_name == null || pmt_name.trim().length() == 0) {
					errorMsgs.add("促銷名稱: 請勿空白");
				} else if(!pmt_name.trim().matches(pmt_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("促銷名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String pmt_intro = req.getParameter("pmt_intro").trim();
				if (pmt_intro == null || pmt_intro.trim().length() == 0) {
					errorMsgs.add("促銷說明請勿空白");
				}	
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				Timestamp pmt_s_date  = null;
				try {
					String pmt_s_Str = req.getParameter("pmt_s_date");
					java.util.Date date = df.parse(pmt_s_Str);
					pmt_s_date = new java.sql.Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					pmt_s_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Timestamp pmt_e_date  = null;
				try {
					String pmt_e_Str = req.getParameter("pmt_e_date");
					java.util.Date date = df.parse(pmt_e_Str);
					pmt_e_date = new java.sql.Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					pmt_e_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String pmt_sts = req.getParameter("pmt_sts").trim();
				if (pmt_sts == null || pmt_sts.trim().length() == 0) {
					errorMsgs.add("促銷狀態請勿空白");
				}else {
					
				}		
				
				Double pmt_discount = null;
				try {
					pmt_discount = new Double(req.getParameter("pmt_discount").trim());
				} catch (NumberFormatException e) {
					pmt_discount = 0.0;
					errorMsgs.add("折扣請填數字.");
				}
				
				//促銷明細新增部分(含商品)
				String[] it_id_array = req.getParameterValues("it_id");//取新增的商品it_id 存入it_id_array陣列裡
				List<PmtDetVO> pmtDetList = new ArrayList<PmtDetVO>();//建立一個新的陣列
				PmtDetVO pmtDetVO = null;//把pmtDetVO預設為空值
				for(String it_id: it_id_array){//再把新增的it_id_array內每個it_id跑for迴圈 
					pmtDetVO = new PmtDetVO();
					pmtDetVO.setPmt_id(pmt_id);
					pmtDetVO.setIt_id(it_id);
					pmtDetList.add(pmtDetVO);//新增到pmtDetVO= 1個pmt_id+1個(it_id)
				}
				//促銷新增部分
				PmtService pmtSvc = new PmtService();//因service有宣告pmtVO所以可以直接用
				PmtVO pmtVO =null; //宣告
 				pmtVO = pmtSvc.getOnePmt(pmt_id); //取值
				pmtVO.setEmp_no(emp_no); //拿來設定回去 用
				pmtVO.setPmt_name(pmt_name); 
				pmtVO.setPmt_intro(pmt_intro);
				pmtVO.setPmt_s_date(pmt_s_date);
				pmtVO.setPmt_e_date(pmt_e_date);
				pmtVO.setPmt_sts(pmt_sts);
				pmtVO.setPmt_discount(pmt_discount);
			//	pmt_pic↓ servlet課本P80。  .available()→去得可用字元
				Part p=req.getPart("upfile");			
				if(!((p.getInputStream().available())==0)){					
					byte[] pmt_pic= new byte[p.getInputStream().available()];
					p.getInputStream().read(pmt_pic,0,pmt_pic.length);
					pmtVO.setPmt_pic(pmt_pic);
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pmtVO", pmtVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/com.pmt.view/update_pmt_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}				
				/***************************2.開始修改資料*****************************************/
				pmtSvc.update(pmtVO, pmtDetList); //拿來用
				PmtEchoServer pmtEchoServer = new PmtEchoServer();
				pmtEchoServer.broadcast("修改啦");
				req.setAttribute("pmtVO", pmtVO); // 資料庫update成功後,正確的的pmtVO物件,存入req
				
			
//				PmtThread pmtThread = new PmtThread();				
//				pmtThread.autoPushPMT(pmt_id, "online", pmt_s_date);
//				pmtThread.autoPushPMT(pmt_id, "offline", pmt_e_date);

				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/com.pmt.view/pmt_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/com.pmt.view/update_pmt_input.jsp");
				failureView.forward(req, res);
			}
		}		
		
		//新增-------------------
        if ("insert".equals(action)) { // 來自addPmt.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_no = new String(req.getParameter("emp_no").trim());
				String[] it_id = req.getParameterValues("it_id");

				String pmt_name = req.getParameter("pmt_name");
				String pmt_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pmt_name == null || pmt_name.trim().length() == 0) {
					errorMsgs.add("促銷名稱：請勿空白");
				} else if(!pmt_name.trim().matches(pmt_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("名稱:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String pmt_intro = req.getParameter("pmt_intro").trim();
				if (pmt_intro == null || pmt_intro.trim().length() == 0) {
					errorMsgs.add("促銷說明：請勿空白");
				}
				
				//假圖片 byte[] pic = new byte[1024]; Part→SERVLET3.0新增的
				Part p=req.getPart("upfile");
				byte[] pmt_pic= new byte[p.getInputStream().available()];
				p.getInputStream().read(pmt_pic,0,pmt_pic.length);
				if(p.getSize()  <= 0 ){
					errorMsgs.add("促銷圖片：請勿空白");
				}
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				Timestamp pmt_s_date  = null;
				try {
					String pmt_s_Str = req.getParameter("pmt_s_date");//取回的pmt_s_date是字串
					java.util.Date date = df.parse(pmt_s_Str);//把字串轉成日期型態
					pmt_s_date = new java.sql.Timestamp(date.getTime());//再把日期型態存到Timestamp型態日期+時間
				} catch (Exception e) {
					pmt_s_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期");					
				}
				
				Timestamp pmt_e_date  = null;
				try {
					String pmt_e_Str = req.getParameter("pmt_e_date");
					java.util.Date date = df.parse(pmt_e_Str);
					pmt_e_date = new java.sql.Timestamp(date.getTime());				
				} catch (Exception e) {
					pmt_e_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");							
				}
				
				String pmt_sts = req.getParameter("pmt_sts").trim();
//				if (pmt_sts == null || pmt_sts.trim().length() == 0) {
//					errorMsgs.add("促銷狀態，請勿空白");
//				}					
				
				Double pmt_discount = null;
				try {
					pmt_discount = new Double(req.getParameter("pmt_discount").trim());
				} catch (NumberFormatException e) {
					pmt_discount = 0.0;
					errorMsgs.add("折扣：請勿空白");
				}

			
				String[] it_id_array =req.getParameterValues("it_id");//取新增的商品it_id 存入it_id_array陣列裡
				if(it_id_array == null){
					errorMsgs.add("已選商品區：請勿空白");
				}
				PmtVO pmtVO = new PmtVO();
				pmtVO.setEmp_no(emp_no);
				pmtVO.setPmt_name(pmt_name);
				pmtVO.setPmt_intro(pmt_intro);
				pmtVO.setPmt_s_date(pmt_s_date);
				pmtVO.setPmt_e_date(pmt_e_date);
				pmtVO.setPmt_sts(pmt_sts);
				pmtVO.setPmt_pic(pmt_pic);
				pmtVO.setPmt_discount(pmt_discount);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pmtVO", pmtVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/com.pmt.view/addPmt.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
//				確認不是空白後再存到PmtDetList
				List<PmtDetVO> pmtDetList = new ArrayList<PmtDetVO>();
				PmtDetVO pmtDetVO = null;
				for(String id: it_id_array){//把新增的列內每個it_id跑for迴圈 
					pmtDetVO = new PmtDetVO();
					pmtDetVO.setIt_id(id);
					pmtDetList.add(pmtDetVO);//新增到pmtDetVO= pmt_id+it_id
				}
				
				/***************************2.開始新增資料***************************************/
				PmtService pmtSvc = new PmtService();
				String pmt_id;
				pmt_id = pmtSvc.addPmt(pmtVO, pmtDetList);

				PmtThread pmtThread = new PmtThread();
				pmtThread.autoPushPMT(pmt_id, "online", pmt_s_date);
				pmtThread.autoPushPMT(pmt_id, "offline", pmt_e_date);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/com.pmt.view/pmt_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);					
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料請勿空白"+e.getMessage());//+e.getMessage()
				RequestDispatcher failureView = req
						.getRequestDispatcher("/com.pmt.view/addPmt.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
		
		//刪除-----------------------------
        if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();			
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			System.out.println(requestURL);
			try {
				/***************************1.接收請求參數***************************************/
				String pmt_id = new String(req.getParameter("pmt_id"));				
				/***************************2.開始刪除資料***************************************/
				PmtService pmtSvc = new PmtService();
				PmtVO pmtVO = pmtSvc.getOnePmt(pmt_id);
				pmtSvc.deletePmt(pmt_id);				
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
        //前端促銷列表點選 連接單一促銷頁面-----------------------------
        if ("checkPmtDetail".equals(action)) { // 來自前端pmtFont_Index.jsp
    		String pmt_id = req.getParameter("pmt_id");
    		PmtService pmtSvc = new PmtService();
    		PmtVO pmtVO = pmtSvc.getOnePmt(pmt_id);
    		req.setAttribute("pmtVO", pmtVO);
		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
    		String url = "/com.pmt.view/PmtCheckDetail.jsp";
    		RequestDispatcher successView = req.getRequestDispatcher(url); 
    		successView.forward(req, res);
		}
        
        if("search_IT_Name".equals(action)){
        	res.setContentType("application/json;charset=UTF-8");
        	PrintWriter out = res.getWriter();
			Gson gson = new Gson();

			com.Product.model.ProductService productSvc = new com.Product.model.ProductService();
			Map<String, ProductVO> itMap;
			
        	String it_name = req.getParameter("it_name");
        	if(it_name == null || it_name.trim()==""){
        		itMap = productSvc.getAllProductMap();//如是空值，就抓全部商品
        	}else{
        		itMap = productSvc.getAllProductMapBy_it_name_Serach(it_name);//輸入商品搜尋就抓相關的商品
        	}
        	
        	out.print(gson.toJson(itMap));
        }
		
	}
			
}


