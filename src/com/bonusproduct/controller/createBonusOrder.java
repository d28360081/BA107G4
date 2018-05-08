package com.bonusproduct.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusproduct.model.BonusProductVO;
import com.bonusproduct.model.BonusproService;
import com.bonusproductorder.model.BonusProductOrderVO;
import com.member.model.MemService;
import com.member.model.MemVO;



/**
 * Servlet implementation class createBonusOrder
 */
@WebServlet("/createBonusOrder")
public class createBonusOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createBonusOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getParameter("action");
		if("createOrder".equals(action)){
			List<String>errMsgs = new LinkedList<String>();
			request.setAttribute("errMsgs",errMsgs);
			try{
			String mem_id = request.getParameter("mem_id");
			String bns_it_id = request.getParameter("bns_it_id");
			System.out.println(bns_it_id);
			String mem_name = request.getParameter("mem_name");
			if(mem_name==null || mem_name.trim().length()==0){
				errMsgs.add("請輸入收件人姓名");
			}
			String mem_ph = request.getParameter("mem_ph");
			String ph_Reg = "^[(0-10)]{10}$";
			if(mem_ph==null || mem_ph.trim().length()==0){
				errMsgs.add("請輸入收件人手機號碼");
			}else if(mem_ph.trim().matches(ph_Reg)){
				errMsgs.add("手機電話號碼格式錯誤");
			}
			String mem_add = request.getParameter("mem_add");
			if(mem_add==null || mem_add.trim().length()==0){
				errMsgs.add("請輸入收件人地址");
			}
			BonusProductOrderVO bnsorVO = new BonusProductOrderVO();
			bnsorVO.setMem_name(mem_name);
			bnsorVO.setMem_ph(mem_ph);
			bnsorVO.setMem_add(mem_add);
			if(!errMsgs.isEmpty()){
				request.setAttribute("bns_it_id", bns_it_id);
				RequestDispatcher faile = request.getRequestDispatcher("/BonusStore/bonusInsertData.jsp");
				faile.forward(request, response);
				return;
			}
			MemService memSrc = new MemService();
			MemVO memVO = memSrc.getOneMem(mem_id);
			BonusproService bnsSrc = new BonusproService();
			BonusProductVO bnsVO = bnsSrc.getOnebns(bns_it_id);
			Integer sum_prc = bnsVO.getBns_it_prc();
			BonusProductOrderVO bnsOrVO = bnsSrc.addOrdeatail(mem_id, sum_prc, "物流運送中", mem_add, mem_ph, mem_name,bns_it_id);
//			HashMap<String, BonusProductVO> map = new HashMap<String, BonusProductVO>();
//			map.put(bns_it_id, bnsVO);
//			bnsSrc.insertOrderwithDetail(bnsorVO, null);
			String bns_rec_id = bnsOrVO.getBns_rec_id();
//			System.out.println(bns_rec_id);
			Integer charge = memVO.getBonus()-sum_prc;
			memVO = memSrc.updateBonus(mem_id, charge);
			memVO = memSrc.updateMemAuth(mem_id);
			memVO = memSrc.getOneMem(mem_id);
			//EMAIL內容
			  String to = memVO.getMem_email();
		      
		      String subject = "訂購商品通知函";
		      
		     
		      
		      
		      String messageText = "親愛的 會員 "+mem_name+" 您好:" + "\n"+"您購買了以下商品:" + "\n"+bnsVO.getBns_it_name()+"\n"+
		    		  				"\n"+"收貨地址:"+mem_add+"\n"+"一共為"+sum_prc+"點紅利"+"\n"+"請確認訂單是否正確，如有任何問題，請聯絡客服中心。"+"\n"+"請保留此單據，方便日後查詢，也謝謝您的支持與愛護!※此信件為系統發出信件，請勿直接回覆";		       

		      MailService mailService = new MailService();
		      //寄出EMAIL
		      mailService.sendMail(to, subject, messageText);
			HttpSession session = request.getSession();
			session.setAttribute("MemVO", memVO);
//			request.setAttribute("BnsOrVO", bnsOrVO);
			request.setAttribute("bns_it_id", bns_it_id);
//			request.setAttribute("bns_rec_id", bns_rec_id);
			
			String url ="BonusStore/bonusOrderDetails.jsp";
			RequestDispatcher success = request.getRequestDispatcher(url);
			success.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
