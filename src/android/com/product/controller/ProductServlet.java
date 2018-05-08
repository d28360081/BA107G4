package android.com.product.controller;

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

import android.com.com.model.ComVO;
import android.com.product.model.ProductService;
import android.com.product.model.ProductVO;

public class ProductServlet extends HttpServlet{
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String outStr = "";
		ProductService productSvc=new ProductService();

		Gson gson = new Gson();
		String action = req.getParameter("action");
		//System.out.println(action);
		
		//動作比對
		if ("getAll".equals(action)) {
			//選全部商店的商品
			List<ProductVO> productList=productSvc.selectAllgogoshop();
			outStr = gson.toJson(productList);

		}else if("getAllOrderByPrice".equals(action)) {
			List<ProductVO> productList=productSvc.selectAllgogoshopOrderByPrice();
			outStr = gson.toJson(productList);
			
		}
		else if("getAllOrderByPriceDESC".equals(action)) {
			List<ProductVO> productList=productSvc.selectAllgogoshopOrderByPriceDESC();
			outStr = gson.toJson(productList);
		}
		else if("getFavPro".equals(action)){
			//還原成 set id 物件
			Set favSet=gson.fromJson(req.getParameter("prosIds"),Set.class);
			Iterator objs =favSet.iterator();
			List<ProductVO> proList=new ArrayList();
			while(objs.hasNext()) {
				String id=(String)objs.next();//String Id
				System.out.println("輸入"+id);
				ProductVO provo=productSvc.findByPrimaryKey(id);
				proList.add(provo);
			}
			outStr = gson.toJson(proList);
			System.out.println("輸出物"+outStr);

		}
		
		res.setContentType(contentType);
		PrintWriter out=res.getWriter();
		//System.out.println("輸出物"+outStr);
		out.print(outStr);
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
