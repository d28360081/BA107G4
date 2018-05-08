package com.article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;

/**
 * Servlet implementation class ArticleEdit
 */
@WebServlet("/ArticleEdit")
@MultipartConfig
public class ArticleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleEdit() {
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
		//article_edit.jsp request
		if("artEdit".equals(action)){
			List<String>errMsgs = new LinkedList<String>();
			request.setAttribute("errMsgs",errMsgs);
			try{
				String art_tlt = request.getParameter("art_tlt");
				if(art_tlt==null || art_tlt.trim().length()==0){
					errMsgs.add("請輸入文章標題");
				}
				Part photo = request.getPart("art_pic1");
				byte[] art_pic1 = null;
				try{
					if (photo != null) {
						InputStream in = photo.getInputStream();
						art_pic1 = new byte[in.available()];
						in.read(art_pic1);
						in.close();
					}
				}
				catch(NullPointerException ne){
					photo.delete();
				}
				String mem_id = request.getParameter("mem_id");
				String art_cnt = request.getParameter("art_cnt");
				if(art_cnt==null || art_cnt.trim().length()==0){
					errMsgs.add("請輸入文章內容");
				}
				
				ArticleVO artVO = new ArticleVO();
				artVO.setArt_tlt(art_tlt);
				artVO.setArt_pic1(art_pic1);
				artVO.setMem_id(mem_id);
				artVO.setArt_cnt(art_cnt);
				
				if(!errMsgs.isEmpty()){
					request.setAttribute("artVO", artVO);
					RequestDispatcher faile = request.getRequestDispatcher("/Article/article_edit.jsp");
					faile.forward(request, response);
					return;
				}
				ArticleService artSrc = new ArticleService();
				artVO = artSrc.addArt(mem_id, art_tlt, art_cnt, art_pic1, null, null);
				request.setAttribute("artVO", artVO);
				RequestDispatcher success = request.getRequestDispatcher("/updateBonus");
				System.out.println("aaaaaaaaa");
				success.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher faile = request.getRequestDispatcher("/Article/article_edit.jsp");
				faile.forward(request, response);
				return;
			}
		}
	}

}
