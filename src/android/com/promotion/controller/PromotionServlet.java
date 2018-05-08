package android.com.promotion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import android.com.main.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.promotion.model.PromotionDAO_interface;
import android.com.promotion.model.PromotionDAO;
import android.com.promotion.model.PromotionService;
import android.com.promotion.model.PromotionVO;


public class PromotionServlet extends HttpServlet {
	private static final String contentType = "text/html;charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String outStr = "";
		PromotionService proSvc=new PromotionService();

		Gson gson = new Gson();
		String action = req.getParameter("action");
		//System.out.println(action);
		
		int imageSize = Integer.parseInt(req.getParameter("imageSize"));
		if ("getAll".equals(action)) {
			proSvc= new PromotionService();
			List<PromotionVO> promotionList=proSvc.getAll();
		
			for (int i = 0; i < promotionList.size(); i++) {
				PromotionVO promotion = promotionList.get(i);
					promotion.setPmtpic(ImageUtil.shrink(promotion.getPmtpic(),
						imageSize));
			}
			
			outStr = gson.toJson(promotionList);

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
