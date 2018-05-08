package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class updateBonus
 */
@WebServlet("/updateBonus")
public class updateBonus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBonus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try{
			String mem_id = request.getParameter("mem_id");
			Integer bonus = Integer.valueOf(request.getParameter("bonus"))+20;
			MemService memSrc = new MemService();
			MemVO memVO = memSrc.updateBonus(mem_id, bonus);
			memVO = memSrc.updateMemAuth(mem_id);
			memVO = memSrc.getOneMem(mem_id);
			HttpSession session = request.getSession();
			session.setAttribute("MemVO", memVO);
			String url = "/Article/articleIndex_h.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
