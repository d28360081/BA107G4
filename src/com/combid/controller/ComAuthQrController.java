package com.combid.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comauth.model.ComQrService;
import com.comauth.model.ComQrVO;
import com.combid.model.ComBidService;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;

/**
 * Servlet implementation class ComAuthQrController
 */
@WebServlet("/ComAuthQrController")
public class ComAuthQrController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComAuthQrController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComBidService cbs=new ComBidService();
        ComNofService cns=new ComNofService();
        ComPantLsService cpls=new ComPantLsService();
		//耍qrcode之後
		String auth_number=request.getParameter("auth_number");
		String com_id=request.getParameter("com_id");
		String mem_id=request.getParameter("mem_id");
		ComQrService cqs=new ComQrService();
		ComQrVO cqv=cqs.select(com_id, mem_id);
		
		//比對與當初放到資料庫的密碼
//		if(cqv.getAuth_number().equals(auth_number)){
//			//相同的話,像代購人發訊息
//			ComNofVO comnofvo=new ComNofVO();
//			String buyerId=cbs.selectBiddingWinner(com_id).getMem_id();
//			//新增訊息
//			comnofvo.setMem_id(buyerId);
//			comnofvo.setNof_cnt("案件編號:"+com_id+"收穫通知,會員:"+mem_id+"以收貨");
//			comnofvo.setNof_tit("收穫通知");
//			comnofvo.setNof_sts("未讀");
//			cns.insert(comnofvo);
//			//更改案件狀態
//			ComPantLsVO cplv=cpls.select(mem_id, com_id);
//			cplv.setCom_sts("以收貨");
//			cpls.update(cplv);
//		}    
//		else{
//			
//		}
		if(cqv.getCom_id()!=null&&cqv.getMem_id()!=null) {
			
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
