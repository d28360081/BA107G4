package com.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComDaoJndi;
import com.com.model.ComVO;





/**
 * Servlet implementation class ImgHandler
 */
@WebServlet("/ImgHandler")
public class ImgHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //name是指要查詢的任務或是促銷的編號
	   String name=request.getParameter("name");
	   //只要去資料庫查詢哪一張圖片
	   String type=request.getParameter("type");
	   ComDaoJndi cdj=new ComDaoJndi();
	   ComVO cv=cdj.selectpic(name);
	   byte[] imgbyte=null;
	   
	   if("COM_PIC1".equals(type)){
		   imgbyte=cv.getCom_pic1();   
	   }
	   else if("COM_PIC2".equals(type)){
		   imgbyte=cv.getCom_pic2();
		   
	   }else if("IT_CHK_PIC1".equals(type)){
		   imgbyte=cv.getIt_chk_pic1();
	   }
	   
	   
	   response.setContentType("image/jpeg");
	   ServletOutputStream sop=response.getOutputStream();
	   sop.write(imgbyte);
	   sop.flush();
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
