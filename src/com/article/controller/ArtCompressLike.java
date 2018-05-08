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
import com.articlecommand.model.ArtcomVO;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class ArtCompressLike
 */
@WebServlet("/ArtCompressLike")
public class ArtCompressLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtCompressLike() {
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
		if("pressmsglike".equals(action)){
			String art_msg_id = request.getParameter("art_msg_id");
			Integer art_msg_like_num = Integer.parseInt(request.getParameter("art_msg_like_num"))+1;
			String art_id = request.getParameter("art_id");
			String mem_id = request.getParameter("mem_id");
			MemService memSrc = new MemService();
			ArticleService artSrc = new ArticleService();
			Integer bonus = memSrc.getOneMem(mem_id).getBonus()-5;
			String mem_id_author = artSrc.findByArt_msg_id(art_msg_id).getMem_id();
			Integer bonus_author = memSrc.getOneMem(mem_id_author).getBonus()+5;
			memSrc.updateBonus(mem_id, bonus);
			memSrc.updateMemAuth(mem_id);
			memSrc.updateBonus(mem_id_author, bonus_author);
			memSrc.updateMemAuth(mem_id_author);
			ArtcomVO artcomVO = artSrc.updatelikenum(art_msg_id, art_msg_like_num);
			ArticleVO artVO = artSrc.findByArt_id(art_id);
			request.setAttribute("artVO", artVO);
			request.setAttribute("artcomVO", artcomVO);
			String url = "/Article/articleMain.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
		}
	}

}
