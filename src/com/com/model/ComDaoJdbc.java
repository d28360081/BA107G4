package com.com.model;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.comclc.model.ComClcVO;
import com.compant.model.ComPantLsVO;

import timeCount.TimeCountTool;
import timeCount.TimeObject;

public class ComDaoJdbc implements ComDaoInterface{
 private String INSERT="insert into COMMISION (COM_ID ,MEM_ID , COM_TIT, COM_CNT,COM_S_DT, COM_E_DT, COM_STS, "
		+ "LT_M_DT, LMT_LCL , LMT_ATD_LV, LMT_AUC_LV,COM_MIN_NUM, COM_MX_NUM, LMT_M_PRC,LMT_DEL_PRC , PUR_S_DATE, "
		+ "PUR_E_DATE, COM_PIC1, COM_PIC2, IT_CHK_PIC1, COM_IT_STS, "
		+ "BNS_NUMBER, COM_PRC, IT_SZ, IT_COL,COM_RMD) values ('COM'||LPAD(TO_CHAR(COM_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
 final String UPDATE_TIT="UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=? WHERE COM_ID=?";
 static final String selectJoinGroupFromComPantsLs ="SELECT * FROM COMMICION_COLLECTION WHERE COM_ID=? AND COM_STS='招募參與'";
 final String SELECT="SELECT * FROM COMMISION WHERE COM_ID=?";
 final String SELECTEDIT="SELECT * FROM COMMISION WHERE MEM_ID=? AND COM_ID=?";
	private static final long SEC=1000;
	private static final long MIN=60000;
	private static final long HOUR=3600000;
	private static final long DAY=86400000;
	
    //嚙踐��隤券�蔬嚙踝�麾���蕭嚙�
	@Override
	public void update(ComVO ComVO) { 	
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE_TIT);
			prst.setString(1, ComVO.getCom_tit());
            prst.setInt(2, ComVO.getLmt_atd_clv());
            prst.setInt(3, ComVO.getLmt_auc_lv());
            prst.setString(4, ComVO.getCom_cnt());
            prst.setTimestamp(5, ComVO.getCom_e_dt());
            prst.setString(6, ComVO.getCom_id());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		}
	@Override
	public void delete(String comid) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement("DELETE FROM COMMISION WHERE COM_ID =?");
			prst.setString(1, comid);
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public ComVO insert(ComVO comvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			String[] generateKey =new String[]{"COM_ID"};
			PreparedStatement prst =c.prepareStatement(INSERT,generateKey);
		
			
			prst.setString(1, comvo.getMem_id());
			prst.setString(2, comvo.getCom_tit());
			prst.setString(3, comvo.getCom_cnt());
			prst.setTimestamp(4, comvo.getCom_s_dt());
			prst.setTimestamp(5, comvo.getCom_e_dt());
			prst.setString(6, comvo.getCom_sts());
			prst.setTimestamp(7, comvo.getLmt_m_dt());
			prst.setString(8, comvo.getLmt_lcl());
			prst.setInt(9, comvo.getLmt_atd_clv());
			prst.setInt(10, comvo.getLmt_auc_lv());
			prst.setInt(11, comvo.getCom_min_num());
			prst.setInt(12, comvo.getCom_mx_num());
			prst.setInt(13, comvo.getLmt_m_prc());
			prst.setInt(14, comvo.getLmt_del_prc());
			prst.setTimestamp(15, comvo.getPur_s_date());
			prst.setTimestamp(16, comvo.getPur_e_date());
			prst.setBytes(17, comvo.getCom_pic1());
			prst.setBytes(18, comvo.getCom_pic2());
			prst.setBytes(19, comvo.getIt_chk_pic1());
			prst.setString(20, comvo.getCom_it_sts());
			prst.setInt(21, comvo.getBns_number());
			prst.setInt(22, comvo.getCom_prc());
			prst.setString(23, comvo.getIt_sz());
			prst.setString(24, comvo.getIt_col());
			prst.setString(25, comvo.getCom_rmd());
			prst.executeUpdate();
			ResultSet rs=prst.getGeneratedKeys();
			while(rs.next()){
				comvo.setCom_id(rs.getString(1));
			}
			c.setAutoCommit(true);
			c.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return comvo;

	}

	@Override
	public ComVO select(String comid) {
		ComVO ComVO =new ComVO();
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			PreparedStatement prst =c.prepareStatement(SELECT);
			prst.setString(1, comid);
			ResultSet rs=prst.executeQuery();
			
			while(rs.next()) {
				ComVO.setCom_id(rs.getString(1));
				ComVO.setMem_id(rs.getString(2));
				ComVO.setCom_tit(rs.getString(3));
				ComVO.setCom_cnt(rs.getString(4));
				ComVO.setCom_s_dt(rs.getTimestamp(5));
				ComVO.setCom_e_dt(rs.getTimestamp(6));
				ComVO.setCom_sts(rs.getString(7));
				ComVO.setLmt_m_dt(rs.getTimestamp(8));
				ComVO.setLmt_lcl(rs.getString(9));
				ComVO.setLmt_atd_clv(rs.getInt(10));
				ComVO.setLmt_auc_lv(rs.getInt(11));
				ComVO.setCom_min_num(rs.getInt(12));
				ComVO.setCom_mx_num(rs.getInt(13));
				ComVO.setLmt_m_prc(rs.getInt(14));
				ComVO.setLmt_del_prc(rs.getInt(15));
				ComVO.setPur_s_date(rs.getTimestamp(16));
				ComVO.setPur_s_date(rs.getTimestamp(17));	
				ComVO.setCom_it_sts(rs.getString(21));
				ComVO.setBns_number(rs.getInt(22));
				ComVO.setCom_prc(rs.getInt(23));
				ComVO.setIt_sz(rs.getString(24));
				ComVO.setIt_col(rs.getString(25));
				ComVO.setCom_rmd(rs.getString(26));
				
		} 
			}catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ComVO;
		 
		
	}




	

	@Override
	public List<ComVO> selectAll() {
		List<ComVO> list=new ArrayList();
		Connection c=null;
		PreparedStatement prst=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BA107G4","123");
			prst =c.prepareStatement("Select * from COMMISION");
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				ComVO comvo=new ComVO();
				comvo.setCom_id(rs.getString(1));
		    	comvo.setMem_id(rs.getString(2));
		    	comvo.setCom_tit(rs.getString(3));
		    	comvo.setCom_cnt(rs.getString(4));
		    	comvo.setCom_s_dt(rs.getTimestamp(5));
		    	comvo.setCom_e_dt(rs.getTimestamp(6));
		    	comvo.setCom_sts(rs.getString(7));
		    	comvo.setLmt_m_dt(rs.getTimestamp(8));
		    	comvo.setLmt_lcl(rs.getString(9));
		    	comvo.setLmt_atd_clv(rs.getInt(10));
		    	comvo.setLmt_auc_lv(rs.getInt(11));
		    	comvo.setCom_min_num(rs.getInt(12));
		    	comvo.setCom_mx_num(rs.getInt(13));
		    	comvo.setLmt_m_prc(rs.getInt(14));
		    	comvo.setLmt_del_prc(rs.getInt(15));
		    	comvo.setPur_s_date(rs.getTimestamp(16));
		    	comvo.setPur_e_date(rs.getTimestamp(17));
		    	comvo.setCom_it_sts(rs.getString(21));
		    	comvo.setBns_number(rs.getInt(22));
		    	comvo.setCom_prc(rs.getInt(23));
		    	comvo.setIt_sz(rs.getString(24));
		    	comvo.setIt_col(rs.getString(25));
		    	comvo.setCom_rmd(rs.getString(26));
		    	comvo.setTimeObject(caculateLeftTime(Calendar.getInstance().getTimeInMillis(), comvo.getCom_e_dt().getTime()));
		    	list.add(comvo);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				if(prst!=null){
			    	prst.close();}  				
				if(c!=null){
				
					c.close();}
			}catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public ComVO selectpic(String comid) {
		ComVO cv=new ComVO();
		Connection c=null;  
		PreparedStatement prst=null;
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			prst=c.prepareStatement("select com_pic1,com_pic2,it_chk_pic1 from COMMISION WHERE com_id=?");
			prst.setString(1, comid);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				cv.setCom_pic1(rs.getBytes(1));
				cv.setCom_pic2(rs.getBytes(2));
				cv.setIt_chk_pic1(rs.getBytes(3));
			}
			
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				if(prst!=null){
			    	prst.close();}  				
				if(c!=null){
				
					c.close();}
			}catch (SQLException e) {

				e.printStackTrace();
			}
		}

	    return cv;
	}
	
	
	@Override
	public String joinselect(String comid, String memid) {
		Connection c=null;  
	    	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
				PreparedStatement prst=c.prepareStatement("Select mem_id from COMMISION_PARTICIPANTS_LIST Where com_id=? and mem_id=?");
			    prst.setString(1, comid);
			    prst.setString(2,memid);
				ResultSet rs=prst.executeQuery();
				while(rs.next()){
					if("".equals(rs.getString(1))){
						return "none";
					}else{
						return "block";
					}
				}
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(c!=null){
					try {
						c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		return "block";
	}
	@Override
	public ComVO select(String comid, String memid) {
		  Connection c=null;
		  ComVO comvo=new ComVO();
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			PreparedStatement prst =c.prepareStatement(this.SELECTEDIT);
			prst.setString(1, memid);
			prst.setString(2, comid);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {	
				comvo.setCom_id(rs.getString(1));
		    	comvo.setMem_id(rs.getString(2));
		    	comvo.setCom_tit(rs.getString(3));
		    	comvo.setCom_cnt(rs.getString(4));
		    	comvo.setCom_s_dt(rs.getTimestamp(5));
		    	comvo.setCom_e_dt(rs.getTimestamp(6));
		    	comvo.setCom_sts(rs.getString(7));
		    	comvo.setLmt_m_dt(rs.getTimestamp(8));
		    	comvo.setLmt_lcl(rs.getString(9));
		    	comvo.setLmt_atd_clv(rs.getInt(10));
		    	comvo.setLmt_auc_lv(rs.getInt(11));
		    	comvo.setCom_min_num(rs.getInt(12));
		    	comvo.setCom_mx_num(rs.getInt(13));
		    	comvo.setLmt_m_prc(rs.getInt(14));
		    	comvo.setLmt_del_prc(rs.getInt(15));
		    	comvo.setPur_s_date(rs.getTimestamp(16));
		    	comvo.setPur_e_date(rs.getTimestamp(17));
		    	comvo.setCom_it_sts(rs.getString(21));
		    	comvo.setBns_number(rs.getInt(22));
		    	comvo.setCom_prc(rs.getInt(23));
		    	comvo.setIt_sz(rs.getString(24));
		    	comvo.setIt_col(rs.getString(25));
		    	comvo.setCom_rmd(rs.getString(26));
		    	
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return comvo;
	}
	@Override
	public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list) {
		   Connection c=null;
		   List<ComVO> list2=new ArrayList<ComVO>();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");		
			for(ComPantLsVO cv:list){
				PreparedStatement prst =c.prepareStatement(selectJoinGroupFromComPantsLs);	
				prst.setString(1,cv.getCom_id());
				ResultSet rs=prst.executeQuery();
				while(rs.next()){
					ComVO ComVO=new ComVO();
					ComVO.setCom_id(rs.getString(1));
					ComVO.setMem_id(rs.getString(2));
					ComVO.setCom_tit(rs.getString(3));
					ComVO.setCom_cnt(rs.getString(4));
					ComVO.setCom_s_dt(rs.getTimestamp(5));
					ComVO.setCom_e_dt(rs.getTimestamp(6));
					ComVO.setCom_sts(rs.getString(7));
					ComVO.setLmt_m_dt(rs.getTimestamp(8));
					ComVO.setLmt_lcl(rs.getString(9));
					ComVO.setLmt_atd_clv(rs.getInt(10));
					ComVO.setLmt_auc_lv(rs.getInt(11));
					ComVO.setCom_min_num(rs.getInt(12));
					ComVO.setCom_mx_num(rs.getInt(13));
					ComVO.setLmt_m_prc(rs.getInt(14));
					ComVO.setLmt_del_prc(rs.getInt(15));
					ComVO.setPur_s_date(rs.getTimestamp(16));
					ComVO.setPur_s_date(rs.getTimestamp(17));	
					ComVO.setCom_it_sts(rs.getString(21));
					ComVO.setBns_number(rs.getInt(22));
					ComVO.setCom_prc(rs.getInt(23));
					ComVO.setIt_sz(rs.getString(24));
					ComVO.setIt_col(rs.getString(25));
					ComVO.setCom_rmd(rs.getString(26));
					list2.add(ComVO);
				}
			}
			
			
			
					
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list2;
		
	}
	@Override
	public List<ComVO> selectJoinBuyFromComPantsLs(List<ComPantLsVO> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ComVO> selectHistory(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ComVO> selectSelf(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeObject caculateLeftTime(long beginTime,long endTime){
		TimeObject tob=new TimeObject();
		long interval=(endTime)-(beginTime);
		
		//剩餘天數
		long totalDay=(interval/DAY);
		interval-=(totalDay*DAY);
		tob.setDay((int)totalDay);
		//剩餘小時
		
		long totalHour=(interval/HOUR);
		
		interval-=(totalHour*HOUR);
		tob.setHour((int)totalHour);
		//剩餘分鐘
		long totalMin=(interval/MIN);
		interval-=(totalMin*MIN);
		tob.setMinute((int)totalMin);
		//剩餘秒數
		long totalSec=(interval/SEC);
		interval-=(totalSec*SEC);
		tob.setSecond((int)totalSec);
	
		return tob;
	}
	



}
