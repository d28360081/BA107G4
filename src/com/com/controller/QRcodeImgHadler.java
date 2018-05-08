package com.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comauth.model.ComQrService;
import com.comauth.model.ComQrVO;

/**
 * Servlet implementation class QRcodeImgHadler
 */
@WebServlet("/QRcodeImgHadler")
public class QRcodeImgHadler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRcodeImgHadler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("image/jpeg");
		byte[] qrByte=null;
		
		if("Qrcode".equals(request.getParameter("type"))){
			String com_id=request.getParameter("com_id");
			String mem_id=request.getParameter("mem_id");
			ComQrService cqs=new ComQrService();
			ComQrVO cqv=cqs.select(com_id, mem_id);
			qrByte=cqv.getQr_code();
			
		}   
		   

		   ServletOutputStream sop=response.getOutputStream();
		   sop.write(qrByte);
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
