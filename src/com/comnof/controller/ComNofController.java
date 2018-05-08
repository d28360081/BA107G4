package com.comnof.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidService;
import com.combid.model.ComBidVO;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;


/**
 * Servlet implementation class ComNofController
 */
@WebServlet("/ComNofController")
public class ComNofController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComNofController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComNofService comnofservice=new ComNofService();
		ComPantLsService compantlsservice=new ComPantLsService();
        MemVO memvo=(MemVO) request.getSession().getAttribute("MemVO");
		String type=request.getParameter("type");
		ComService comservice=new ComService();
		ComBidService combidservice=new ComBidService();
		List<ComNofVO> comnofvolist=null;
		
		if("checkNotification".equals(type)){
		
		comnofvolist=comnofservice.selectAllUnread(memvo.getMem_id());

		request.setAttribute("type", "checkNotification");
        request.setAttribute("joinList", comnofvolist);
	
		request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
		return;
		
		}
		else if("checkNotificationDetail".equals(type)){
		 ComNofVO comnofno=comnofservice.select(request.getParameter("nof_id"));
		 request.setAttribute("ComNofVO", comnofno);
		 request.getRequestDispatcher("/com.comclc.view/ComNofDetail.jsp").forward(request, response);
		return;
		}
		else if("WinningBidCheck".equals(request.getParameter("type"))){
			
			List<ComBidVO> list=combidservice.selectWinner(memvo.getMem_id());
			
		    request.setAttribute("type", "checkNotification");
			request.setAttribute("WinnerList", list);
			request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
			return;
		}
//			else if("panticipantCheck".equals(request.getParameter("type"))) {
//			String com_id=request.getParameter("com_id");
//			    List<ComPantLsVO>list= compantlsservice.selectAllinCase(com_id);
//			    request.setAttribute("type", "");
//				request.setAttribute("pantcipantList", list);
//				request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
//		        return;
//		}
		
		else if("GroupEditJoint".equals(request.getParameter("type"))){
			
			List<ComPantLsVO> list=compantlsservice.selectAll(memvo.getMem_id());
			
			request.setAttribute("type", "checkNotification");
			request.setAttribute("JointButList", list);
			request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
			return;
		}
		else if("ajax".equals(request.getParameter("type"))){
			
			if(request.getParameter("comnof_id")!=null){
				String comnof_id=request.getParameter("comnof_id");
				ComNofVO comnofno=comnofservice.select(comnof_id);
				comnofno.setNof_sts("已讀");
				comnofservice.update(comnofno);
			}
			
			return;
		}
		
		
		
		request.setAttribute("joinList", comnofvolist);
		
		request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
