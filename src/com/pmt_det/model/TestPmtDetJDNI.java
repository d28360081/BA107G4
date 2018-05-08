package com.pmt_det.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testpmtder")
public class TestPmtDetJDNI extends HttpServlet{
	public static final long serizLversionUID =1L;

	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("test/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		PmtDetService pmtdetSvc = new PmtDetService();
		
		//新增
//		PmtDetVO pmtdetVO1 = new PmtDetVO();
//		pmtdetVO1.setPmt_id("PMT0014");//要看資料有無重複
//		pmtdetVO1.setIt_id("IT001");
//		pmtdetSvc.addPmtDet(pmtdetVO1);			
		//刪除
//		pmtdetSvc.deletePmtDet("PMT001","IT001");
		//查詢
		String pmt_id = "PMT013";
		List<PmtDetVO> pmtdetList = pmtdetSvc.getByPMT_ID(pmt_id);
		for(PmtDetVO pmtDetVO: pmtdetList){
			System.out.println(pmtDetVO.getPmt_id());
			System.out.println(pmtDetVO.getIt_id());
			System.out.println("=======================");
		}
//			
//		String it_id = "IT001";
//		List<PmtDetVO> pmtdetVO2 = pmtdetSvc.getByIT_ID(it_id);
//		for(int i=0; i<=pmtdetVO.size();i++){//陣列長度
//			System.out.println(pmtdetVO.toString());
//		}
	
//				String pmt_id = "PMT013";
//				List<PmtDetVO> pmtdetVO = pmtdetSvc.getByPMT_ID(pmt_id);
////				for(int i=0; i<=pmtdetVO.size();i++){//陣列長度
////					System.out.println(pmtdetVO.toString());
//					String srt = pmtdetVO.toString();
//					String[] str2s = srt.split("],");
//					for(String str2:str2s){
//						System.out.println(str2);
//					}
					
//			}
//					String it_id = "IT001";
//					List<PmtDetVO> pmtdetVO2 = pmtdetSvc.getByIT_ID(it_id);
//					for(int i=0; i<=pmtdetVO.size();i++){//陣列長度
//						System.out.println(pmtdetVO.toString());
			
//			String it_id = "IT001";
//			List<PmtDetVO> pmtdetVO1 = pmtdetSvc.getByIT_ID(it_id);
//			
//			for(int i=0; i<pmtdetVO1.size();i++){//陣列長度
//				
//				System.out.println("第"+i+"葛:"+pmtdetVO1.get(i).getIt_id());
//				String it_id2=pmtdetVO1.get(i).getIt_id();
//				
				
//			}
//		PmtDetVO pmtdetVO2 = pmtdetSvc.getOnePmtDet_IT("IT002");
//		System.out.println(pmtdetVO1.getPmt_id()+","+pmtdetVO1.getIt_id()+",");
//		out.println(pmtdetVO1.getPmt_id()+",");
//		out.println(pmtdetVO1.getIt_id()+",");

//		out.println("---------------------------");
//		
		//查詢全部
//		List<PmtDetVO> list = pmtdetSvc.getAll();
//		for(PmtDetVO allPmtDet : list){
			//2種顯示方式：sysout 顯示在下面 out.顯示在網頁
//			out.println(allPmtDet.getPmt_id()+",");//顯示在網頁
//			out.println(allPmtDet.getIt_id()+",");

//			out.println();
//			System.out.println(allPmtDet.getPmt_id()+",");//顯示在下面
//			System.out.println(allPmtDet.getIt_id()+",");

//		}
		
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
		doGet(request, response);
	}
}
