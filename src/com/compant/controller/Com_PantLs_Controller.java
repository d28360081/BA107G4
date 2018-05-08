package com.compant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.com.model.ComDaoJndi;
import com.com.model.ComService;
import com.com.model.ComVO;
import com.compant.model.ComPantLsDAOJNDI;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;

import timeCount.TimeCountTool;

/**
 * Servlet implementation class Com_PantLs_Controller
 */
@WebServlet("/Com_PantLs_Controller")
public class Com_PantLs_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Com_PantLs_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession();
		String type=request.getParameter("type");
		String com_id=request.getParameter("com_id");
		MemVO mv=(MemVO)request.getSession().getAttribute("MemVO");
		ComPantLsService cps=new ComPantLsService();
		ComService cs=new ComService();
		ComPantLsVO cplv=new ComPantLsVO();
		ComVO cv=cs.select(com_id);
		
		int com_mx_num=cs.select(com_id).getCom_mx_num();
		int cur_mx_num=cps.selectAll(com_id).size();
		
		if("joinCommision".equals(type)){
			//代表人數已滿
			if(com_mx_num==cur_mx_num){
		    //跳至錯誤葉面
		    request.setAttribute("Message", "人數已滿");
			request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			return;
			}
			//成功加入代購
			else if(com_mx_num>cur_mx_num){
			cplv.setCom_id(com_id);
			cplv.setMem_id(mv.getMem_id());
			cplv.setCom_sts("未付款");
			cplv.setDlt_adds(mv.getAddress());
			cplv.setMem_sts("參與中");
			cplv.setCom_it_num(0);
			cps.insert(cplv);
			    if(cps.selectAll(com_id).size()==cs.select(com_id).getCom_mx_num()){
			    	ComVO voteCV=new TimeCountTool().getTimecounter().getCountDownMap().get(com_id);
			    	new TimeCountTool().getTimecounter().getVoteMap().put(voteCV.getCom_id(), voteCV);
			    	new TimeCountTool().getTimecounter().getCountDownMap().remove(com_id);
			    	
			    }
			request.setAttribute("Message", "成功加入");
			request.setAttribute("ComVO", cv);
			request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);
			return;
			}
		
		}
		//成功退出代購
		else if("leaveCommision".equals(type)){
			cps.delete(mv.getMem_id(), com_id);
			
			request.setAttribute("Message", "成功刪除");
			request.setAttribute("ComVO", cv);
			request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			return;
		
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
