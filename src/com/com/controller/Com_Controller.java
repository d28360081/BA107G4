package com.com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cocmt.model.ComComtDAOJNDI;
import com.cocmt.model.ComComtVO;
import com.com.model.ComDaoJdbc;
import com.com.model.ComDaoJndi;
import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidDAOJndi;
import com.combid.model.ComBidVO;
import com.compant.model.ComPantLsDAOJNDI;
import com.compant.model.ComPantLsVO;
import com.member.model.MemDAO;
import com.member.model.MemVO;

import timeCount.JedisManager;
import timeCount.TimeCountTool;

/**
 * Servlet implementation class Com_Controller
 */
@MultipartConfig
@WebServlet("/Com_Controller")
public class Com_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Com_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		HttpSession hs=request.getSession();
		MemVO mv=(MemVO) hs.getAttribute("MemVO");
		ComVO cv=null;
		ComService cs=new ComService();
		String com_id=request.getParameter("com_id");
		request.setCharacterEncoding("utf-8");
		String page=request.getParameter("page");
		
		if("checklist".equals(request.getParameter("type"))){
			
			if(mv==null){
				//先假設有會員
			    mv=new MemDAO().findByPrimaryKey("M000007");
				hs.setAttribute("MemVO", mv);
				//
			}
			
			TimeCountTool timecounttool=((TimeCountTool)getServletContext().getAttribute("TimeCountTool"));
			request.setAttribute("ComVOlist", cs.selectAll());
			request.setAttribute("type", "TuanGo");
			RequestDispatcher rsd=request.getRequestDispatcher("/Com/ComIndex.jsp?page="+page);
	        rsd.forward(request,response);
            return;
		}        
		//查看代購詳情
		else if("checkdetail".equals(request.getParameter("type"))){
			RequestDispatcher rsd=request.getRequestDispatcher("/Com/ComCheckDetail.jsp");
	
			 //判斷是否登入會員,如果有登入再判斷是否在代購案件中
			if(null!=mv){
				ComComtDAOJNDI ccd=new ComComtDAOJNDI();
				ComPantLsDAOJNDI cpl=new ComPantLsDAOJNDI();
				ComBidDAOJndi cbdjn=new ComBidDAOJndi();
				List<ComPantLsVO> compantlsvolist= cpl.selectAll(com_id);
				List<ComBidVO> combidvolist=cbdjn.selectAll(com_id);
				List<ComPantLsVO> compantlsmemsts=cpl.selectSTS(com_id, "未付款");
				   
				//是否已經參加代購
				
				if("none".equals(cs.joinselect(com_id,mv.getMem_id()))){
//					System.out.println(com_id+"有參加");
					//如果已經加入代購,尚可退出代購
					if("招募參與".equals(cs.select(com_id).getCom_sts())
						||"招募代購人".equals(cs.select(com_id).getCom_sts())){
						
					
					    request.setAttribute("unjoin", "block");
					    request.setAttribute("join", "none");
					
					//如果已經加入代購,案件狀態不可退出代購
				     }else{
				    	 request.setAttribute("join", "none");
					    request.setAttribute("unjoin","none");
				     }
				//有登入但沒參加代購
				}else{
//					System.out.println(com_id+"沒參加");
					request.setAttribute("join", "block");
					request.setAttribute("unjoin", "none");
					
				}
					
					cv=cs.select(request.getParameter("com_id"));
					List<ComComtVO> comcomtlist= ccd.selectAll(request.getParameter("com_id"));
					
					request.setAttribute("ComVO", cv);
					request.setAttribute("ComComtVOlist", comcomtlist);
					request.setAttribute("ComPantLsVOList", compantlsvolist);
					request.setAttribute("ComBidVOList", combidvolist);
					request.setAttribute("ComPantLsMemSts", compantlsmemsts);
					
					
			//沒登入 訪客身分
			}else{
//				System.out.println("沒登入");
				request.setAttribute("join","none");
				request.setAttribute("unjoin", "none");
			}	
			rsd.forward(request,response);

		}
		else if("goDaiGo".equals(request.getParameter("type"))){
			List<ComVO> list=new ArrayList<ComVO>();
			Set<String > Keyset=new TimeCountTool().getTimecounter().getDaiGoCountDownMap().keySet();
			for(String s:Keyset) {
				
				list.add(new TimeCountTool().getTimecounter().getDaiGoCountDownMap().get(s));
			}
					
			request.setAttribute("ComVOlist", new ComService().selectDaiGo());
			request.setAttribute("type", "DaiGo");
			RequestDispatcher rsd=request.getRequestDispatcher("/Com/ComIndex.jsp?page="+page);
			
	        rsd.forward(request,response);
		}
		
		else if("checkDaiGodetail".equals(request.getParameter("type"))){

			ComVO Daigocv=cs.select(request.getParameter("com_id")); 
			
			request.setAttribute("ComVO", Daigocv);
			request.setAttribute("type", "checkDaiGodetail");
			request.getRequestDispatcher("/com.combid.view/DaiGoCheckDetail.jsp").forward(request, response);
			
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
