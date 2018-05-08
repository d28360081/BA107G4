package com.article.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleHibernateService;
import com.article.model.ArticleVO_Hibernate;

/**
 * Servlet implementation class searchByTlt
 */
@WebServlet("/searchByTlt")
public class searchByTlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchByTlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		List<String>errMsgs = new LinkedList<String>();
		request.setAttribute("errMsgs",errMsgs);
		ArticleHibernateService artSrc = new ArticleHibernateService();
		String value = request.getParameter("value");
		List<ArticleVO_Hibernate> list=artSrc.searchArt(value);
		request.setAttribute("list", list);
		String url = "/Article/articleIndex_h.jsp";
		RequestDispatcher success = request.getRequestDispatcher(url);
		success.forward(request, response);
	}

}
