package com.bonusproduct.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusproduct.model.BonusproService;
import com.bonusproductorder.model.BonusProductOrderVO;

/**
 * Servlet implementation class createOrderDetail
 */
@WebServlet("/createOrderDetail")
public class createOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createOrderDetail() {
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
		BonusProductOrderVO bnsorde = (BonusProductOrderVO)request.getAttribute("BnsOrVO");
		String bns_rec_id = bnsorde.getBns_rec_id();
		System.out.println(bns_rec_id);
		String bns_it_id = request.getAttribute("bns_it_id").toString();
		BonusproService bnsSrc = new BonusproService();
		BonusProductOrderdetailVO bnsord =bnsSrc.addBnsOrderDetail(bns_rec_id, bns_it_id, 1);
		request.setAttribute("bnsord", bnsord);
		String url = "/member/memberIndex2.jsp";
		RequestDispatcher success = request.getRequestDispatcher(url);
		success.forward(request, response);
		
	}

}
