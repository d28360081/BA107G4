package com.comt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cocmt.model.ComComtService;
import com.cocmt.model.ComComtVO;
import com.com.model.ComService;
import com.com.model.ComVO;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.member.model.MemVO;


/**
 * Servlet implementation class ComComtController
 */
@WebServlet("/ComComtController")
public class ComComtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComComtController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String type=request.getParameter("type");
      String com_id=request.getParameter("com_id");
      ComNofService cns=new ComNofService();
      //留言檢舉
     

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		ComService comservice=new ComService();
		try {
		if("comment".equals(type)) {
			String com_id=request.getParameter("com_id");
			ComVO comvo=comservice.select(com_id);
			MemVO memvo=(MemVO) request.getSession().getAttribute("MemVO");
			ComComtService comcomtservice=new ComComtService();
			ComComtVO comcomtvo=new ComComtVO();
			comcomtvo.setCom_id(com_id);
			comcomtvo.setComt_cnt(request.getParameter("editor1"));
			comcomtvo.setMem_id(memvo.getMem_id());
			comcomtservice.insert(comcomtvo);
			request.setAttribute("Message", "留言成功");
			if("招募參與".equals(comvo.getCom_sts())){
			request.getRequestDispatcher("/Com_Controller?com_id="+com_id+"&type=checkdetail").forward(request, response);
			return;
			}
			else if("招募代購人".equals(comvo.getCom_sts())){
			request.getRequestDispatcher("/Com_Controller?com_id="+com_id+"&type=checkDaiGodetail").forward(request, response);
			return;                                                               
			}
		}   
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

}
