package com.article.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.member.controller.updateMemAuth;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class PressLikeArt
 */
@WebServlet("/PressLikeArt")
public class PressLikeArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PressLikeArt() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getParameter("action");	
		if("presslike".equals(action)) {
			String art_id = request.getParameter("art_id");
			String mem_id = request.getParameter("mem_id");
			String mem_id_author = request.getParameter("mem_id_author");
			Integer bonus = Integer.parseInt(request.getParameter("bonus"))-5;
			Integer like_num = Integer.parseInt(request.getParameter("like_num"))+1;
			MemService memSrc = new MemService();
			Integer bonus_auth = memSrc.getOneMem(mem_id_author).getBonus()+5;
			memSrc.updateBonus(mem_id_author, bonus_auth);
			memSrc.updateMemAuth(mem_id_author);
			ArticleService artSrc = new ArticleService();
			MemVO memVO = memSrc.updateBonus(mem_id, bonus);
			memVO = memSrc.updateMemAuth(mem_id);
			memVO = memSrc.getOneMem(mem_id);
			ArticleVO artVO = artSrc.updateLike(art_id, like_num);
			HttpSession session = request.getSession();
			session.setAttribute("MemVO", memVO);
			request.setAttribute("artVO", artVO);
			String url = "/Article/articleMain.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
		}
	}
}
