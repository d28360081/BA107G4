package com.combid.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.combid.model.ComBidService;
import com.combid.model.ComBidVO;
import com.comnof.model.ComNofService;
import com.comnof.model.ComNofVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemDAO;
import com.member.model.MemVO;

import timeCount.TimeCountTool;
import timeCount.TimeObject;
import timeCount.TimeWs;

/**
 * Servlet implementation class CombidController
 */
@WebServlet("/CombidController")
public class CombidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CombidController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,RuntimeException {
	request.setCharacterEncoding("utf-8");
    try{
	ComService comservice=new ComService();
	MemDAO memberservice=new MemDAO();	
	ComBidService combidservice=new ComBidService();
	ComPantLsService compantlsservice=new ComPantLsService();
	TimeWs tw=new TimeWs();
	ComNofService comnofservice=new ComNofService();
		//代購主辦人進入編輯頁面
		if("BiddingEdit".equals(request.getParameter("type"))) {
		 request.setAttribute("ComVO", comservice.select(request.getParameter("com_id")));
		 request.getRequestDispatcher("/Com/Com_Frame.jsp").forward(request, response);
		 
	 }else if("vote".equals(request.getParameter("type"))){

		 String com_id=request.getParameter("com_id");
		 //投給誰
		 String mem_id=request.getParameter("mem_id");
		 //誰來頭
		 MemVO mv=(MemVO) request.getSession().getAttribute("MemVO");
		 ComPantLsVO compantls=compantlsservice.select(mv.getMem_id(), com_id);
		 ComVO comvo=comservice.select(com_id);
		 if("已投票".equals(compantls.getMem_sts())){
			 request.setAttribute("ComVO", comservice.select(com_id));
			 request.setAttribute("Message", "不能重複投票");
			 request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			 return;
		 }
		 compantls.setMem_sts("已投票");
		 compantlsservice.update(compantls);
		 ComBidVO combidvo=combidservice.select(mem_id, com_id);
		 combidvo.setN_o_v(combidvo.getN_o_v()+1);
		 combidservice.update(combidvo);
		 //算出總票數
		 
		 List<ComBidVO> combidvolist=combidservice.selectAll(com_id);
		 int totalBallot=0;
		 for(ComBidVO cbv:combidvolist){
			 totalBallot+=cbv.getN_o_v();
		 }
		 
		 //全部人都已經投票
	
		 if(compantlsservice.selectAllinCase(com_id).size()==totalBallot){
			
			 List<ComBidVO> list2=combidservice.selectAll(com_id);
			 ComBidVO winnerbidvo=new ComBidVO();
			 ComNofVO comnofvo=new ComNofVO();
			 int WinnerVote=0;
			 String WinnerName=null;
			 //找出得票最高人
			 for(ComBidVO combidvo2:list2){
				 WinnerVote=combidvo2.getN_o_v();
                 
				 if(combidvo2.getN_o_v()>=WinnerVote){
					 WinnerVote=combidvo2.getN_o_v();
					 WinnerName=combidvo.getMem_id();
				 }
			 }
			 //調整案件狀態,調整投票人得標
			 for(ComBidVO combidvo2:list2){
				 if(combidvo2.getMem_id().equals(WinnerName)){
					 combidvo2.setAt_sts("得標");
				 }else{
				 combidvo2.setAt_sts("沒得標");
				 tw.sendInfo(combidvo2.getMem_id(), "案件"+comvo.getCom_tit()+"未得標,請再接再厲");
				 }
				 combidservice.update(combidvo2);
			 }
			
			 winnerbidvo=combidservice.select(WinnerName, com_id);
			 comnofvo.setMem_id(winnerbidvo.getMem_id());
			 comnofvo.setNof_tit("得標通知");
			 comnofvo.setNof_cnt("恭喜"+com_id+"已經得標");
			 comnofvo.setNof_sts("未讀");
			 comnofservice.insert(comnofvo);
			 tw.sendInfo(winnerbidvo.getMem_id(),"恭喜您,在案件:"+comvo.getCom_tit()+"中得標");
			
			 for(ComPantLsVO compantlsvo:compantlsservice.selectAllinCase(com_id)){
				 ComNofVO comnofvo2=new ComNofVO();
				 comnofvo2.setMem_id(compantlsvo.getMem_id());
				 comnofvo2.setNof_tit("投票結果通知");
				 comnofvo2.setNof_cnt("恭喜,會員"+WinnerName+",在代購案件"+com_id+"已經得標");
				 comnofvo2.setNof_sts("未讀");
				 comnofservice.insert(comnofvo2);
				 tw.sendInfo(winnerbidvo.getMem_id(),"案件:"+comvo.getCom_tit()+",已經順利找到代購人");
			 }
			 //發送完訊息後.
			 comvo=comservice.select(com_id);
			 comvo.setCom_sts("投票結束");
			 comservice.update(comvo);
			 
		 }
		 
		 request.setAttribute("totalBallot", totalBallot);
		 request.setAttribute("com_id", com_id);
		 request.getRequestDispatcher("/com.comclc.view/Com_CheckJointFrame.jsp").forward(request, response);
		 return;
	 }
		
	
	
	 }catch(Exception e) {
		 e.printStackTrace();
		 throw new RuntimeException(e);
	 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComService comservice=new ComService();
		request.setCharacterEncoding("utf-8");
		ComBidService combidservice=new ComBidService();
		try {
		if("EditBiddingFinish".equals(request.getParameter("type"))) {
		    StringBuffer sb=new StringBuffer();
			ComVO comvo=comservice.select(request.getParameter("com_id"));
			String com_rmd=request.getParameter("com_rmd");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			sb.append(request.getParameter("LT_M_DT"));
			sb.append(" ");
			sb.append(request.getParameter("LT_M_DT_time"));
			Date lmt_m_dt=sdf.parse(sb.toString());
			comvo.setLmt_m_dt(new Timestamp(lmt_m_dt.getTime()));
			comvo.setCom_rmd(com_rmd);
			comvo.setCom_sts("招募代購人");
			
			//一但進入代購案件,則倒數時間重製,改為減少代購時間
			TimeObject tob=new TimeObject();
			tob.setLeftTime(lmt_m_dt.getTime());
		
			comvo.setTimeObject(tob);
			comservice.update(comvo);
//			new TimeCountTool().getTimecounter().getDaiGoCountDownMap().put(comvo.getCom_id(),comvo);
			request.setAttribute("ComVO", comvo);
			request.setAttribute("Message", "編輯完成");
			request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);
			return;
			
		}
		else if("BiddingAudit".equals(request.getParameter("type"))) {
		
			String com_id=request.getParameter("com_id");
			MemVO memvo=(MemVO) request.getSession().getAttribute("MemVO");
			ComVO comvo=comservice.select(com_id);
			//選出代購案件後,找出當初設定的限制等級
			Integer lmt_auc_lv=comvo.getLmt_auc_lv();
			//最低運費
			Integer lmt_del_prc=comvo.getLmt_del_prc();
			Integer lmt_m_prc=comvo.getLmt_m_prc();
			request.setAttribute("ComVO", comvo);
			Integer auc_prc=Integer.valueOf(request.getParameter("auc_prc"));
			Integer auc_del_prc=Integer.valueOf(request.getParameter("auc_del_prc"));
		    
			
			if(lmt_auc_lv>memvo.getBonus()) {
				request.setAttribute("Message", "<strong>競標失敗</strong><br><h3>權限不足,限制權限為:"+lmt_auc_lv+"<br> 會員權限為"+memvo.getBonus()+"</h3>");
		
				request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			    return;
			}
			if(lmt_del_prc>auc_prc) {
				request.setAttribute("Message", "<strong>競標失敗</strong><br><h3>運費不得小於最低價格<br>最低運費:"+lmt_del_prc+"</h3>");			
				request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			    return;
			}
			if(lmt_m_prc>auc_del_prc) {
				request.setAttribute("Message", "<strong>競標失敗</strong><br><h3>競標價格過低:"+lmt_m_prc+"<br> 會員權限為"+auc_del_prc+"</h3>");	
				request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			    return;
			}
			
			
			
			else if(request.getParameter("auc_del_prc")==null||request.getParameter("auc_prc")==null){
				request.setAttribute("ComVO", comvo);
				request.setAttribute("Message", "不可為空值");
				request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
				return;
			}
			
			
			else {
				ComBidVO combidvo=new ComBidVO();
				combidvo.setMem_id(memvo.getMem_id());
				combidvo.setCom_id(comvo.getCom_id());
			    combidvo.setAuc_del_prc(Double.valueOf(request.getParameter("auc_del_prc")));
				combidvo.setAuc_prc(Double.valueOf(request.getParameter("auc_prc")));
				combidvo.setAt_sts("競標中");
				combidvo.setN_o_v(0);
				combidservice.insert(combidvo);
				new TimeWs().sendInfo(comvo.getMem_id(), "案件:"+comvo.getCom_tit()+"有人加入競標囉");
                request.setAttribute("Message", "成功參與競標     "+comvo.getCom_id());
                request.getRequestDispatcher("/com.compant.view/ComPantLsCorrect.jsp").forward(request, response);

                
			}
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Message", "輸入格式錯誤,重新輸入");			
			request.getRequestDispatcher("/com.compant.view/ComPantLsError.jsp").forward(request, response);
			throw new RuntimeException(e);
			
		}
	}

}
