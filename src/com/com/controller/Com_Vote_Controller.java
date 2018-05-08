package com.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComService;
import com.combid.model.ComBidService;
import com.combid.model.ComBidVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;



/**
 * Servlet implementation class Com_Vote_Controller
 */
@WebServlet("/Com_Vote_Controller")
public class Com_Vote_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Com_Vote_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String com_id=request.getParameter("com_id");
		String mem_id=((MemVO)request.getSession().getAttribute("MemVO")).getMem_id();
		ComBidService cbs=new ComBidService();
		ComPantLsService comservice=new ComPantLsService();
		String type=request.getParameter("type");
		
		if("vote".equals(type)){
			String urltoVote="/Com/VoteView.jsp";	
			ComPantLsVO cplv=new ComPantLsVO();
			cplv.setMem_id(mem_id);
			cplv.setCom_id(com_id);
			cplv.setMem_sts("已投票");
			comservice.update(cplv);
			request.getRequestDispatcher(urltoVote).forward(request, response);
		}
		else if("comeToVote".equals(type)){
			List<ComBidVO> list=cbs.selectAll(com_id);
			request.setAttribute("ComBidList", list);
			request.getRequestDispatcher("/Com/VoteView.jsp").forward(request, response);
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
