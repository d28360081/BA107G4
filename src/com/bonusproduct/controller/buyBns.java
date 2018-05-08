package com.bonusproduct.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bonusproduct.model.BonusProductVO;
import com.bonusproduct.model.BonusproService;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class buyBns
 */
@WebServlet("/buyBns")
public class buyBns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyBns() {
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
		String action = request.getParameter("action");
		if("buybns".equals(action)){
			List<String>errMsgs = new LinkedList<String>();
			request.setAttribute("errMsgs",errMsgs);
			String mem_id = request.getParameter("mem_id");
			String bns_it_id = request.getParameter("bns_it_id");
			MemService memSrc = new MemService();
			MemVO memVO = memSrc.getOneMem(mem_id);
			BonusproService bnsSrc = new BonusproService();
			BonusProductVO bnsVO = bnsSrc.getOnebns(bns_it_id);
			if(memVO.getBonus()<bnsVO.getBns_it_prc())
				errMsgs.add("您的紅利點數不足QQ");
			if(!errMsgs.isEmpty()){
				RequestDispatcher faile = request.getRequestDispatcher("/BonusStore/bonusstoreIndex.jsp");
				faile.forward(request, response);
				return;
			}
			
			request.setAttribute("bns_it_id", bns_it_id);
			String url = "/BonusStore/bonusInsertData.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
		}
		if("back".equals(action)){
			try {
				HttpSession session = request.getSession();
				String location = (String) session.getAttribute("location");
				if (location != null) {
					System.out.println("zzzzzzzzzzzzzzzzzzz");
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					response.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			response.sendRedirect(request.getContextPath() + "/BonusStore/bonusstoreIndex.jsp");
		}
		if("getProduct".equals(action)){
			String bns_rec_id = request.getParameter("bns_rec_id");
			BonusproService bnsSrc = new BonusproService();
			bnsSrc.updateOrderSts("已收貨", bns_rec_id);
			String url = "/BonusStore/bonusOrderDetails.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
		}
	}

}
