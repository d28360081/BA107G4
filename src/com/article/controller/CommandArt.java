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

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.articlecommand.model.ArtcomVO;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class CommandArt
 */
@WebServlet("/CommandArt")
public class CommandArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandArt() {
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
		List<String>errMsgs = new LinkedList<String>();
		request.setAttribute("errMsgs",errMsgs);
		String action = request.getParameter("action");
		ArticleService artSrc = new ArticleService();
		MemService memSrc = new MemService();
		if("insertCom".equals(action)){
			String art_id = request.getParameter("art_id");
			String mem_id = request.getParameter("mem_id");
			String art_msg_cnt = request.getParameter("art_msg_cnt");
			if(art_msg_cnt==null || art_msg_cnt.trim().length()==0){
				errMsgs.add("請輸入留言內容");
			}
			ArtcomVO artcomVO = new ArtcomVO();
			artcomVO.setArt_msg_cnt(art_msg_cnt);
			ArticleVO artVO = new ArticleVO();
			artVO.setArt_id(art_id);
			MemVO memVO = new MemVO();
			memVO.setMem_id(mem_id);
			if(!errMsgs.isEmpty()){
				request.setAttribute("artcomVO", artcomVO);
				request.setAttribute("artVO", artVO);
				request.setAttribute("MemVO", memVO);
				RequestDispatcher faile = request.getRequestDispatcher("/Article/articleMain.jsp");
				faile.forward(request, response);
				return;
			}
			artcomVO = artSrc.addCom(art_id, mem_id, art_msg_cnt, null);
			artVO = artSrc.findByArt_id(art_id);
			memVO = memSrc.getOneMem(mem_id);
			request.setAttribute("artcomVO", artcomVO);
			request.setAttribute("artVO", artVO);
			String url = "/Article/articleMain.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
		}
	}

}
