package com.comclc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComDaoJndi;
import com.com.model.ComService;
import com.com.model.ComVO;
import com.comclc.model.ComClcDAOJNDI;
import com.comclc.model.ComClcVO;
import com.comnof.model.ComNofService;
import com.compant.model.ComPantLsDAOJNDI;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;

import timeCount.TimeCountTool;
import timeCount.TimeCounter;

/**
 * Servlet implementation class Com_ComClc_Controller
 */
@WebServlet("/Com_ComClc_Controller")
public class Com_ComClc_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Com_ComClc_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TimeCountTool timecounttool=new TimeCountTool();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MemVO mv=((MemVO)request.getSession().getAttribute("MemVO"));
		String type=request.getParameter("type");
		String com_id=request.getParameter("com_id");	
		String  mem_id=mv.getMem_id();
		ComService cs=new ComService();
		ComPantLsService cps=new ComPantLsService();
		List<ComPantLsVO> list=cps.selectAll(mem_id);
		List<ComVO> selectlist=null;
		ComVO cv=null;
		ComClcDAOJNDI ccdj=new ComClcDAOJNDI();
		ComClcVO ccv=new ComClcVO();
		
		//加入收藏
		if("addCollection".equals(type)){	
			cv=cs.select(com_id);
			ccv.setCom_id(com_id);
			ccv.setMem_id(mem_id);
			ccdj.insert(ccv);
			request.setAttribute("Message", "加入收藏");
			request.setAttribute("ComVO", cv);
			request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);
			return;
			
		}
		// 參與中團購
		else if("joinParty".equals(type)){
			selectlist=cs.selectJoinGroupFromComPantsLs(list);
			request.setAttribute("type", "joinParty");
		//參與中代購
		}else if("joinBuy".equals(type)){
		   selectlist=cs.selectJoinBuyFromComPantsLs(list);
		   request.setAttribute("type", "joinBuy");
		   //查看歷史事件
		}else if("lookupHistory".equals(type)){		
		   selectlist=cs.selectHistory(mem_id);
			//查看自我案件
		}else if("editSelfItem".equals(type)){
			selectlist=cs.selectSelf(mem_id);
			request.setAttribute("type", "deleteItem");

		}else if("deleteItem".equals(type)){	
			
			cv= cs.select(com_id);
			cv.setCom_sts("下架");
			cs.update(cv);
			selectlist=cs.selectSelf(mem_id);
			request.setAttribute("type", "deleteItem");
			timecounttool.getTimecounter().getCountDownMap().remove(cv.getCom_id());
			
		}else if("changeState".equals(type)){
			cv=cs.select(com_id);
			request.setAttribute("ComVO", cv);
			request.getRequestDispatcher("/Com/Com_Edit.jsp").forward(request, response);
			return;
		
		}else if("changeFinish".equals(type)){
			
			//查看收藏
		}else if("checkCollection".equals(type)){
			List<ComClcVO> collectionlist=ccdj.selectAll(mem_id);
			selectlist=new ArrayList<ComVO>();
			for(ComClcVO ccvo:collectionlist){
				cv=cs.select(ccvo.getCom_id());
				selectlist.add(cv);
			}
			request.setAttribute("type", "removeCollection");
		}else if("removeCollection".equals(type)){
			ccdj.delete(mem_id, request.getParameter("com_id"));
			cv=cs.select(request.getParameter("com_id"));
			request.setAttribute("Message", "移除收藏");
			request.setAttribute("ComVO", cv);
			
		}
		
		request.setAttribute("joinList", selectlist);
		request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		ComService comservice=new ComService();
		
		if("search".equals(type)){
			String com_id=request.getParameter("keyword");
			
			List<ComVO> list=comservice.search(com_id);
			if(list==null){
				System.out.println(123);
				request.setAttribute("Message", "找不到案件喔");
			}else{
				request.setAttribute("ComVOlist", list);
			}
		
			request.setAttribute("type", "TuanGo");
			request.getRequestDispatcher("/Com/ComIndex.jsp?page=1").forward(request, response);;
			return;
		

			
		}
	}

}
