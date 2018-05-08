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
import com.articlereply.model.ArtreVO;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class ReplyArt
 */
@WebServlet("/ReplyArt")
public class ReplyArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyArt() {
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
		if("replyart".equals(action)) {
			try {
				String art_id = request.getParameter("art_id");
				String mem_id = request.getParameter("mem_id");
				String re_cnt = request.getParameter("re_cnt");
				if(re_cnt==null || re_cnt.trim().length()==0) {
					errMsgs.add("請輸入回復文章內容");
				}
				ArtreVO artreVO = new ArtreVO();
				artreVO.setRe_cnt(re_cnt);
				ArticleVO artVO = new ArticleVO();
				artVO.setArt_id(art_id);
				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				if(!errMsgs.isEmpty()) {
					request.setAttribute("artreVO", artreVO);
					request.setAttribute("artVO", artVO);
					request.setAttribute("memVO", memVO);
					RequestDispatcher faile = request.getRequestDispatcher("/Article/articleMain.jsp");
					faile.forward(request, response);
					return;
				}
				artreVO = artSrc.addRe(art_id, mem_id, re_cnt, null);
				artVO = artSrc.findByArt_id(art_id);
				memVO = memSrc.getOneMem(mem_id);
				request.setAttribute("artreVO", artreVO);
				request.setAttribute("artVO", artVO);
				String url = "/Article/articleMain.jsp";
				RequestDispatcher success = request.getRequestDispatcher(url);
				success.forward(request, response);
			}catch(Exception e) {}
		}
	}

}
