package com.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.com.controller.pswGenerator;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emppms.model.EmpPmsService;
import com.emppms.model.EmpPmsVO;
import com.emppmscls.model.EmpPmsClsVO;

import StoreOrderController.MailService;

/**
 * Servlet implementation class EmpController
 */
@MultipartConfig
@WebServlet("/EmpController")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpService empservice=new EmpService();
    private EmpPmsService emppmsservice=new EmpPmsService();
    
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String urltoEmpIndex="/com.emp.view/empIndex.jsp";
		EmpService empsrv=new EmpService();
		List<EmpVO> selectlist=null;
		List<EmpPmsVO> emppmsvolist=null;
		
		if("logout".equals(request.getParameter("type"))){
			request.getSession().removeAttribute("EmpVO");
			
		}
		//權限管理
		else if("AuthManage".equals(request.getParameter("type"))){
			selectlist=empservice.selectAll();
			request.setAttribute("selectlist", selectlist);
			request.setAttribute("type", "AuthManage");
			
		}
		else if("EditAuth".equals(request.getParameter("type"))){
			request.setAttribute("type", "AuthEdit");
			String emp_no=request.getParameter("emp_no");
			emppmsvolist=emppmsservice.selectByPrimaryKey(emp_no);
			EmpVO empvo=empservice.selectEmp(request.getParameter("emp_no"));
			request.setAttribute("EmpPmsVOlist", emppmsvolist);
			request.setAttribute("EmpVO2", empvo);
		}
		
		else if("EstablishNewMember".equals(request.getParameter("type"))){
			request.setAttribute("type", "EstablishNewMember");
		}
		
		request.getRequestDispatcher(urltoEmpIndex).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String urlToEmpIndex="/com.emp.view/empIndex.jsp";
		List<EmpVO> selectlist=null;
		List<EmpPmsClsVO> EmpPmsClsList=null;
		EmpVO ev=null;
		EmpPmsVO epv=null;
		
		//登入判斷
		if("Login".equals(request.getParameter("type"))){
		String emp_id=request.getParameter("emp_id");
		String emp_psw=request.getParameter("emp_psw");
			if(empservice.selectByPrimaryKey(emp_id,emp_psw)){
			 EmpVO empvo=empservice.selectEmp(emp_id, emp_psw);
			 request.getSession().setAttribute("EmpVO", empvo);
			 request.setAttribute("Message", "登入成功");	 
			}else{
			 request.setAttribute("Message", "登入失敗");
			}
           request.setAttribute("type", "Login");
           
		} else if("editFinish".equals(request.getParameter("type"))){
			ev=empservice.selectEmp(request.getParameter("emp_no"));
			
			EmpPmsClsList=new ArrayList<EmpPmsClsVO>();
			String emp_no=ev.getEmp_no();
			Part part=request.getPart("Emp_Pic");
			
			byte[] empbyte=new byte[part.getInputStream().available()];
			part.getInputStream().read(empbyte);
			ev.setEmp_nm(request.getParameter("emp_nm"));
			ev.setEmp_pic(empbyte);
			empservice.update(ev);
			String[] authOption=request.getParameterValues("authOption");
			emppmsservice.deleteAll(emp_no);
			if(authOption!=null){
					for(String s:authOption){
						epv=new EmpPmsVO();
						epv.setEmpno(emp_no);
						epv.setPmsid(s);
						emppmsservice.insert(epv);
					}
			}
		  request.setAttribute("EmpVO2", ev);
		  request.setAttribute("EmpPmslist", emppmsservice.selectByPrimaryKey(emp_no));
		  request.setAttribute("type", "Editfinish");
		 
		} else if ("establishFinish".equals(request.getParameter("type"))){
			MailService mailservice=new MailService();
			String emp_mail=request.getParameter("emp_mail");
			pswGenerator pswg=new pswGenerator();
			String emp_psw=pswg.generate();
			ev=new EmpVO();
			ev.setEmp_nm(request.getParameter("emp_nm"));
			ev.setEmp_psw(emp_psw);
			
			String messageText="密碼通知: 恭喜您申請GoGoShop後臺使用者已獲得許可,以下為您的密碼:"+emp_psw;
			
			mailservice.sendMail(emp_mail, "密碼通知", messageText);
			
			Part p=request.getPart("Emp_Pic");
			byte[] EmpPicbyte=new byte[p.getInputStream().available()];
			p.getInputStream().read(EmpPicbyte, 0, EmpPicbyte.length);
			ev.setEmp_pic(EmpPicbyte);
			ev.setEmp_sts("在職");
			empservice.insert(ev);
			request.setAttribute("EstablishEmp", ev);
		    
			response.sendRedirect(request.getContextPath()+"/EmpController?type=AuthManage");
			
			return;
		}
		
		
		request.getRequestDispatcher(urlToEmpIndex).forward(request, response);
	}

}
