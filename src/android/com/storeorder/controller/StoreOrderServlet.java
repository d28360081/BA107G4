package android.com.storeorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import android.com.product.model.ProductService;
import android.com.product.model.ProductVO;
import android.com.storeReceiptDetail.model.StoreReceiptDetailVO;
import android.com.storeorder.model.StoreOrderService;
import android.com.storeorder.model.StoreOrderVO;

public class StoreOrderServlet extends HttpServlet{
	private static final String contentType = "text/html;charset=UTF-8";
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String outStr = "";
		StoreOrderService storeOrderSvc=new StoreOrderService();

		Gson gson = new Gson();
		String action = req.getParameter("action");
		System.out.println(action);
		
		//動作比對
		if ("insertOrder".equals(action)) {
			StoreOrderVO storeOrderVO=gson.fromJson(req.getParameter("storeOrderVO"),StoreOrderVO.class);
			Type listType=new TypeToken<List<StoreReceiptDetailVO>>() {}.getType();
			List <StoreReceiptDetailVO> receiptDetailVOList=gson.fromJson(req.getParameter("recList"),listType);
			
			
		
			System.out.println("輸入1"+storeOrderVO.getBuyer_name());
	
			
			
			storeOrderSvc.insertStoreOrderWithStoreReceiptDetail(storeOrderVO,receiptDetailVOList);
//			 outStr=storeOrderVO.getDlv_id();
			outStr="99999";

		}
		res.setContentType(contentType);
		PrintWriter out=res.getWriter();
		System.out.println("輸出物"+outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


}
