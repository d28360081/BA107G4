package com.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

/**
 * Servlet implementation class EmpImgHandler
 */
@WebServlet("/EmpImgHandler")
public class EmpImgHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpImgHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String table_name=request.getParameter("table_name");
		
		if("emp".equals(table_name)){
			String emp_no=request.getParameter("emp_no");
			EmpService emps=new EmpService();
			EmpVO empvo=emps.selectEmp(emp_no);
			response.getOutputStream().write(empvo.getEmp_pic());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
