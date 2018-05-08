package com.com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.com.model.ComDaoJndi;
import com.com.model.ComService;
import com.com.model.ComVO;

import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;

import oracle.sql.DATE;
import timeCount.TimeCountTool;
import timeCount.TimeObject;

/**
 * Servlet implementation class Com_Establish_Controller
 */
@MultipartConfig
@WebServlet("/Com_Establish_Controller")
public class Com_Establish_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Com_Establish_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,RuntimeException {
		request.setCharacterEncoding("utf-8");
		TimeCountTool timecounttool= new TimeCountTool();

		if("start".equals(request.getParameter("type"))){
			String mem_id=((MemVO)request.getSession().getAttribute("MemVO")).getMem_id();
			if(mem_id!=null){
				request.getRequestDispatcher("/Com/Com_Establish.jsp").forward(request, response);
			}else{
				request.setAttribute("Message", "請先登入會員");
				request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			}
			
		}
		
		//靽格
		else if("edit".equals(request.getParameter("type"))){
			
				try{             
			    MemVO mv=(MemVO) request.getSession().getAttribute("MemVO");
			    TimeHandler th=new TimeHandler();
				ComVO cv=new ComVO();
				ComService cs=new ComService();
				
				String com_cnt=request.getParameter("com_cnt");
				String com_id=request.getParameter("com_id");
				String com_tit=request.getParameter("com_tit");
//				String com_s_dtorg=request.getParameter("com_s_dt");
//				String combegintime=request.getParameter("combegintime");
				String com_e_dt=request.getParameter("com_e_dt");
				String comendtime=request.getParameter("comendtime");
				//頧������葡�鞎澆��韏�
//				Timestamp begintime=th.toTimeStamp(com_s_dtorg, combegintime);
				Timestamp endtime=th.toTimeStamp(com_e_dt, comendtime);
				//
				String it_col=request.getParameter("it_col");
				String it_sz=request.getParameter("it_sz");
				
				String lmt_lcl=request.getParameter("lmt_lcl");
				Integer lmt_atd_clv=Integer.parseInt(request.getParameter("lmt_atd_clv"));
				Integer lmt_auc_lv=Integer.parseInt(request.getParameter("lmt_auc_lv"));
				Integer com_min_num=Integer.parseInt(request.getParameter("com_min_num"));
				Integer com_mx_num=Integer.parseInt(request.getParameter("com_mx_num"));
				Integer lmt_del_prc=Integer.parseInt(request.getParameter("lmt_del_prc"));
               
				
				byte[] pic1=new byte[request.getPart("com_pic2").getInputStream().available()];
				
				byte[] pic2=new byte[request.getPart("com_pic2").getInputStream().available()];
				request.getPart("com_pic1").getInputStream().read(pic1, 0, pic1.length);
				request.getPart("com_pic2").getInputStream().read(pic2, 0, pic2.length);
				
			
				cv.setCom_id(com_id);
				cv.setCom_cnt(com_cnt);
				cv.setCom_pic1(pic1);
				cv.setCom_pic2(pic2);
				cv.setCom_tit(com_tit);
				cv.setMem_id(mv.getMem_id());
				cv.setCom_s_dt(cs.select(com_id).getCom_s_dt());
				cv.setCom_e_dt(endtime);
				cv.setCom_sts("招募參與");
				cv.setLmt_lcl(lmt_lcl);
				cv.setLmt_atd_clv(lmt_atd_clv);
				cv.setCom_mx_num(com_mx_num);
				cv.setCom_min_num(com_min_num);
				cv.setLmt_m_prc(0);
				cv.setLmt_del_prc(lmt_del_prc);
				cv.setLmt_auc_lv(lmt_auc_lv);
				cv.setBns_number(0);
				cv.setCom_prc(0);
				cv.setIt_col(it_col);
				cv.setIt_sz(it_sz);
				cv.setCom_cnt(com_cnt);
				cv.setCom_min_num(com_min_num);
				cv.setCom_mx_num(com_mx_num);
				cv.setLmt_del_prc(lmt_del_prc);
				cv.setCom_pic1(pic1);
				cv.setCom_pic2(pic2);
				cs.update(cv);
			  request.setAttribute("Message", "<strong>編輯完成</strong><br><h3>案件標題:"+cv.getCom_tit()+"</h3>");
			  request.setAttribute("ComVO", cv);
		      request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);;
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("Message", "<strong>編輯失敗</strong><br><h3>請重新編輯</h3>");
					request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);;
					
				}
				
		}
		
		else if("establish".equals(request.getParameter("type"))){
		try{
        ComPantLsVO cpls=new ComPantLsVO();
	    MemVO mv=(MemVO) request.getSession().getAttribute("MemVO");
	    TimeHandler th=new TimeHandler();//�����極�憿
		ComVO cv=new ComVO();
		ComService cs=new ComService();
		ComPantLsService cplsrv=new ComPantLsService();
		
		
		String com_tit=request.getParameter("com_tit");
//		String com_s_dtorg=request.getParameter("com_s_dt");
//		String combegintime=request.getParameter("combegintime");
		String com_e_dt=request.getParameter("com_e_dt");
		String comendtime=request.getParameter("comendtime");
		//頧�����”��鞎澆��韏�
//		Timestamp begintime=th.toTimeStamp(com_s_dtorg, combegintime);
		Timestamp endtime=th.toTimeStamp(com_e_dt, comendtime);
		String lmt_m_prc=request.getParameter("lmt_m_prc");
		String it_col=request.getParameter("it_col");
		String it_sz=request.getParameter("it_sz");
		String com_cnt=request.getParameter("com_cnt");
		String lmt_lcl=request.getParameter("lmt_lcl");
		Integer lmt_atd_clv=Integer.parseInt(request.getParameter("lmt_atd_clv"));
		Integer lmt_auc_lv=Integer.parseInt(request.getParameter("lmt_auc_lv"));
		Integer com_min_num=Integer.parseInt(request.getParameter("com_min_num"));
		Integer com_mx_num=Integer.parseInt(request.getParameter("com_mx_num"));
		Integer lmt_del_prc=Integer.parseInt(request.getParameter("lmt_del_prc"));

		Collection<Part> partlist=request.getParts();
		System.out.println(request.getPart("com_pic1")==null);
        System.out.println(request.getPart("com_pic1").getInputStream().available());
		for(Part p:partlist){
			String s=null;
			
			if(p.getContentType()!=null){
				
				
				byte[] picbyte=null;
				if("com_pic1".equals(p.getName())){
					
					picbyte=new byte[p.getInputStream().available()];
					p.getInputStream().read(picbyte, 0, picbyte.length);
					cv.setCom_pic1(picbyte);
				}
				else if("com_pic2".equals(p.getName())){
				
					picbyte=new byte[p.getInputStream().available()];
					p.getInputStream().read(picbyte, 0, picbyte.length);
					cv.setCom_pic2(picbyte);
				}
			}
		}
		cv.setCom_tit(com_tit);
		cv.setMem_id(mv.getMem_id());
		cv.setCom_s_dt(new Timestamp(new Date().getTime()));
		cv.setCom_e_dt(endtime);
		cv.setCom_sts("招募參與");
		cv.setLmt_m_dt(cv.getCom_e_dt());
		cv.setLmt_lcl(lmt_lcl);
		cv.setLmt_atd_clv(lmt_atd_clv);
		cv.setCom_mx_num(com_mx_num);
		cv.setCom_min_num(com_min_num);
		cv.setLmt_m_prc(Integer.valueOf(lmt_m_prc));
		cv.setLmt_del_prc(lmt_del_prc);
		cv.setLmt_auc_lv(lmt_auc_lv);
		cv.setBns_number(0);
		cv.setCom_prc(0);
		cv.setIt_col(it_col);
		cv.setIt_sz(it_sz);
		cv.setCom_cnt(com_cnt);
		cv.setCom_min_num(com_min_num);
		cv.setCom_mx_num(com_mx_num);
		cv.setLmt_del_prc(lmt_del_prc);
		TimeObject tob=new TimeObject();
		tob.setLeftTime(endtime.getTime());
		cv.setTimeObject(tob);
		cs.insert(cv);

		cpls.setCom_id(cv.getCom_id());
		cpls.setMem_id(cv.getMem_id());
		cpls.setCom_it_num(0);
		cpls.setMem_sts("參與中");
		cplsrv.insert(cpls);
		
		timecounttool.getTimecounter().getCountDownMap().put(cv.getCom_id(), cv);
		
	  request.setAttribute("Message", "<strong>創建成功</strong><br><h3>案件編號:"+cv.getCom_tit()+"</h3>");
	  request.setAttribute("ComVO", cv);
      request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);;
		}catch(Throwable e){
			e.printStackTrace();
			request.setAttribute("Message", "<strong>創建失敗</strong><br><h3>請重新嘗試</h3>");
			request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);;
			throw new RuntimeException(e.getMessage());
			
		}
		}
		
		
		
	}

}
