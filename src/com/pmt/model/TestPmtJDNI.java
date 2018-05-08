package com.pmt.model;
//新增時右鍵選擇servlet類別新增 就會自動跑出
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestPmtJDNI")
public class TestPmtJDNI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");//在網頁顯示html
		//下面兩行等於上面一行
//		res.setCharacterEncoding("UTF-8");//轉成文字檔顯示在網頁()
//		res.setHeader("Content-Type", "text/html");
		
		
		PrintWriter out = res.getWriter();
		PmtService pmtSvc = new PmtService();
//
//		byte[] pic = new byte[10];	//因只是測試跑的成功嗎?所以可以放假資料進去就好	
//		PmtVO pmtVO1 = new PmtVO();
//		pmtVO1.setEmp_no("EMP002");
//		pmtVO1.setPmt_name("你好");
//		pmtVO1.setPmt_intro("你好");	
//		pmtVO1.setPmt_pic(pic);
//		pmtVO1.setPmt_s_date(java.sql.Timestamp.valueOf("2005-01-01 12:00:00.0"));
//		pmtVO1.setPmt_e_date(java.sql.Timestamp.valueOf("2005-03-01 11:59:59.0"));			
//		pmtVO1.setPmt_sts("已上架");		
//		pmtSvc.addPmt(pmtVO1);
//		System.out.println("ok");


//		PmtVO pmtVO2 = pmtSvc.getOnePmt("PMT001");
//		pmtVO2.setPmt_name("恩恩");
//		pmtSvc.updatePmt(pmtVO2);


//		String str = "PMT045";
//		pmtSvc.deletePmt(str);
//		out.println(str + "恩恩");
		

//		PmtVO pmtVO3 = pmtSvc.getOnePmt("PMT001");
//		out.println(pmtVO3.getPmt_id() + ",");
//		out.println(pmtVO3.getEmp_no() + ",");
//		out.println(pmtVO3.getPmt_name() + ",");
//		out.println(pmtVO3.getPmt_intro() + ",");//		
//		out.println(pmtVO3.getPmt_s_date() + ",");
//		out.println(pmtVO3.getPmt_e_date() +",");
//		out.println(pmtVO3.getPmt_sts());		
//		out.println("---------------------");
//		
	
//		List<PmtVO> list = pmtSvc.getAll();
//		for (PmtVO aPmt : list) {
//			out.println(aPmt.getPmt_id() + ",");
//			out.println(aPmt.getEmp_no() + ",");
//			out.println(aPmt.getPmt_name() + ",");
//			out.println(aPmt.getPmt_intro() + ",");//		
//			out.println(aPmt.getPmt_s_date() + ",");
//			out.println(aPmt.getPmt_e_date()+ ",");
//			out.println(aPmt.getPmt_sts());
//			out.println();
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
