package com.article.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;

/**
 * Servlet implementation class deleteArt
 */
@WebServlet("/deleteArt")
public class deleteArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteArt() {
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
		if("deleteart".equals(action)){
			try{
			String art_id = request.getParameter("art_id");
			String art_sts = "下架";
			ArticleService artSrc = new ArticleService();
			ArticleVO artVO = artSrc.cahgeSts(art_id, art_sts);
			request.setAttribute("artVO", artVO);
			String url ="/member/memberIndex_article.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
