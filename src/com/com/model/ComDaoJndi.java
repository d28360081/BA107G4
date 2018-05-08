package com.com.model;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.comapl.model.ComAplVO;
import com.comclc.model.ComClcVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;

import timeCount.TimeCountTool;
import timeCount.TimeObject;

public class ComDaoJndi implements ComDaoInterface{
	 private String INSERT="insert into COMMISION (COM_ID ,MEM_ID , COM_TIT, COM_CNT,COM_S_DT, COM_E_DT, COM_STS, "
				+ "LT_M_DT, LMT_LCL , LMT_ATD_LV, LMT_AUC_LV,COM_MIN_NUM, COM_MX_NUM, LMT_M_PRC,LMT_DEL_PRC , PUR_S_DATE, "
				+ "PUR_E_DATE, COM_PIC1, COM_PIC2, IT_CHK_PIC1, COM_IT_STS, "
				+ "BNS_NUMBER, COM_PRC, IT_SZ, IT_COL,COM_RMD) values ('COM'||LPAD(TO_CHAR(COM_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		 final String UPDATE_TIT="UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=?,com_sts=?,LT_M_DT=?,COM_RMD=? WHERE COM_ID=?";
		 static final String selectJoinGroupFromComPantsLs ="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS='招募參與' OR COM_ID=? AND COM_STS='等待主辦人編輯代購訊息'";
		 final String selectJoinBuyFromComPantsLs="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS!='招募參與' and com_sts!='等待主辦人編輯代購訊息' AND COM_STS!='下架' AND COM_STS!='結案' AND COM_STS!='時間內未達成團購人數'";
		 final String SELECTHISTORY="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS='結案'";
		 final String SELECTSELF="SELECT * FROM COMMISION WHERE MEM_ID=? and com_sts='招募參與' or MEM_ID=? and com_sts='等待主辦人編輯代購訊息'";
		 final String SELECT="SELECT * FROM COMMISION WHERE COM_ID=?";
		 final String SELECTEDIT="SELECT * FROM COMMISION WHERE MEM_ID=? AND COM_ID=?";
		 static DataSource ds;
		 long now=Calendar.getInstance().getTimeInMillis();
			static {
				
				try {
					Context context=new javax.naming.InitialContext();
					ds=(DataSource) context.lookup("java:comp/env/jdbc/BA107G4");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
					
		 
		
			@Override
			public void update(ComVO ComVO) { 	
				Connection c=null;
				try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement(this.UPDATE_TIT);

					prst.setString(1, ComVO.getCom_tit());
//					System.out.println(ComVO.getCom_tit());
		            prst.setInt(2, ComVO.getLmt_atd_clv());
//		            System.out.println(ComVO.getLmt_atd_clv());
		            prst.setInt(3, ComVO.getLmt_auc_lv());
//		            System.out.println(ComVO.getLmt_auc_lv());
		            prst.setString(4, ComVO.getCom_cnt());
//		            System.out.println(ComVO.getCom_cnt());
		            prst.setTimestamp(5, ComVO.getCom_e_dt());
//		            System.out.println(ComVO.getCom_e_dt());
		            prst.setString(6, ComVO.getCom_sts());
//		            System.out.println(ComVO.getCom_sts());
		            if(ComVO.getLmt_m_dt()!=null) {
		            	  prst.setTimestamp(7, ComVO.getLmt_m_dt());
		            }else {
		            	prst.setTimestamp(7, new Timestamp(new Date().getTime()));
		            }
		          
//		            System.out.println(new Timestamp(new Date().getTime()));
		            prst.setString(8, ComVO.getCom_rmd());
		      
		            prst.setString(9, ComVO.getCom_id());
//		            System.out.println(ComVO.getCom_id());
//		            System.out.println(123);
//					System.out.println(");
//					System.out.println(123);
		            prst.executeUpdate();
					c.commit();
//					System.out.println(123);
					c.setAutoCommit(true);
					
				} catch (Exception e) {
					System.out.println(123);
					if(c!=null){
						try {
							c.rollback();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							
							e1.printStackTrace();
							throw new RuntimeException(e1);
						}
					}
					e.printStackTrace();
					throw new RuntimeException(e);
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
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("DELETE FROM COMMISION WHERE COM_ID =?");
					prst.setString(1, comid);
					prst.executeUpdate();
					c.setAutoCommit(true);
					c.commit();
				} catch (SQLException e) {
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
					c=ds.getConnection();
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
					
				} catch (SQLException e) {
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
						
							e.printStackTrace();
							throw new RuntimeException (e.getMessage());
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
					c=ds.getConnection();
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
						ComVO.setTimeObject(new TimeCountTool().caculateLeftTime(ComVO.getCom_s_dt().getTime(), ComVO.getCom_e_dt().getTime()));
						
				} 
					}catch (SQLException e) {
					
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
				PreparedStatement prst=null;
				Connection c=null;
				try {
					c=ds.getConnection();
					prst =c.prepareStatement("Select * from COMMISION where com_sts='招募參與' and Com_e_dt>sysdate");
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
				    	TimeObject to=new TimeObject();
				    	to.setLeftTime(comvo.getCom_e_dt().getTime());

				    	comvo.setTimeObject(to);
				    	
				    	list.add(comvo);
					}
					
				} catch (SQLException e) {
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
				PreparedStatement prst=null;
				Connection c=null;
				try {
					c=ds.getConnection();
					prst=c.prepareStatement("select com_pic1,com_pic2,it_chk_pic1 from COMMISION WHERE com_id=?");
					prst.setString(1, comid);
					ResultSet rs=prst.executeQuery();
					while(rs.next()){
						cv.setCom_pic1(rs.getBytes(1));
						cv.setCom_pic2(rs.getBytes(2));
						cv.setIt_chk_pic1(rs.getBytes(3));
					}
					
				
				} catch (SQLException e) {
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
					c=ds.getConnection();
						PreparedStatement prst=c.prepareStatement("Select mem_id from COMMISION_PARTICIPANTS_LIST Where com_id=? and mem_id=?");
					    prst.setString(1,comid);
					    prst.setString(2,memid);
						ResultSet rs=prst.executeQuery();
						if(rs.next()){
							return "none";
						}else{
							return "block";
						}
						
						
					} catch (SQLException e) {
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
				  ComVO comvo=new ComVO();
				  Connection c=null;
					try {
					c=ds.getConnection();
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
					
				} catch (SQLException e) {
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
					c=ds.getConnection();		
					for(ComPantLsVO cv:list){
						PreparedStatement prst =c.prepareStatement(selectJoinGroupFromComPantsLs);	
						prst.setString(1,cv.getCom_id());
						prst.setString(2, cv.getCom_id());
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
					
					
					
							
				}catch(SQLException e){
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
				 Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					c=ds.getConnection();		
					for(ComPantLsVO cv:list){
						PreparedStatement prst =c.prepareStatement(selectJoinBuyFromComPantsLs);	
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
					
					
					
							
				}catch(SQLException e){
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
			public List<ComVO> selectHistory(String mem_id) {
				   Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					    c=ds.getConnection();		
						PreparedStatement prst =c.prepareStatement(SELECTHISTORY);	
						prst.setString(1,mem_id);
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
				}catch(SQLException e){
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
			public List<ComVO> selectSelf(String mem_id) {
				 Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					    c=ds.getConnection();		
						PreparedStatement prst =c.prepareStatement(SELECTSELF);	
						prst.setString(1,mem_id);
						prst.setString(2, mem_id);
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
				}catch(SQLException e){
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
			
			public List<ComVO> selectDaiGo() throws RuntimeException{
				 Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					    c=ds.getConnection();		
						PreparedStatement prst =c.prepareStatement("select * from commision where com_sts='招募代購人'and LT_M_DT>sysdate");	
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
							TimeObject tob=new TimeObject();
							tob.setLeftTime(ComVO.getLmt_m_dt().getTime());
							ComVO.setTimeObject(tob);
							
							list2.add(ComVO);
						}
				}catch(SQLException e){
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					if(c!=null){
						try {
							c.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				}
				return list2;
			}
			
			public List<ComVO> search(String com_id){
				 Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					    c=ds.getConnection();		
						PreparedStatement prst =c.prepareStatement("select * from commision where com_tit like ? and com_sts='招募參與' or com_tit like ? and com_sts='招募代購人'");
						prst.setString(1, "%"+com_id+"%");
						prst.setString(2, "%"+com_id+"%");
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
							TimeObject tob=new TimeObject();
							tob.setLeftTime(ComVO.getLmt_m_dt().getTime());
							ComVO.setTimeObject(tob);
							
							list2.add(ComVO);
						}
				}catch(SQLException e){
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					if(c!=null){
						try {
							c.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				}
				return list2;
				
			}
			
			public ComVO selectOnlineDaiGo(String com_id){
				  ComVO comvo=new ComVO();
				  Connection c=null;
					try {
						c=ds.getConnection();
					PreparedStatement prst =c.prepareStatement(this.selectJoinBuyFromComPantsLs);
					prst.setString(1, com_id);				
					ResultSet rs=prst.executeQuery();
					boolean i=rs.next();
					System.out.println(i);
					if(i) {	
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
					
				} catch (SQLException e) {
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
			public ComPantLsVO selectinsertcom(ComVO comvo,MemVO memvo){
				Connection c=null;
				ComPantLsService compantlsservice=new ComPantLsService();
				ComPantLsVO compantlsvo=new ComPantLsVO();
				try {
					c=ds.getConnection();
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
					if(rs.next()){
						comvo.setCom_id(rs.getString(1));
					}
					compantlsvo.setCom_id(comvo.getCom_id());
					compantlsvo.setMem_id(memvo.getMem_id());
					compantlsvo.setCom_sts("未付款");
					compantlsvo.setMem_sts("參與中");
					compantlsservice.insert(compantlsvo);
					
					c.setAutoCommit(true);
					c.commit();
					
					
					
				} catch (SQLException e) {
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
						
							e.printStackTrace();
							throw new RuntimeException (e.getMessage());
						}
					}
				}
				return compantlsvo;
			}
		
			public void updatePIC(ComVO ComVO) { 	
				Connection c=null;
				try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					
					
					if(ComVO.getIt_chk_pic1()==null){
						PreparedStatement prst =c.prepareStatement("UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=?,com_sts=?,LT_M_DT=?,COM_RMD=?,COM_PIC1=?,COM_PIC2=? WHERE COM_ID=?");
						prst.setString(1, ComVO.getCom_tit());
			            prst.setInt(2, ComVO.getLmt_atd_clv());
			            prst.setInt(3, ComVO.getLmt_auc_lv());
			            prst.setString(4, ComVO.getCom_cnt());
			            prst.setTimestamp(5, ComVO.getCom_e_dt());
			            prst.setString(6, ComVO.getCom_sts());
			            prst.setTimestamp(7, new Timestamp(new Date().getTime()));
			            prst.setString(8, ComVO.getCom_rmd());
			            prst.setBytes(9, ComVO.getCom_pic1());
			            prst.setBytes(10, ComVO.getCom_pic2());
			            prst.setString(11, ComVO.getCom_id());
					    prst.executeUpdate();
					    c.setAutoCommit(true);
						c.commit();
					}
					else {
						PreparedStatement prst =c.prepareStatement("UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=?,com_sts=?,LT_M_DT=?,COM_RMD=?,COM_PIC1=?,COM_PIC2=?,IT_CHK_PIC1=? WHERE COM_ID=?");
						prst.setString(1, ComVO.getCom_tit());
			            prst.setInt(2, ComVO.getLmt_atd_clv());
			            prst.setInt(3, ComVO.getLmt_auc_lv());
			            prst.setString(4, ComVO.getCom_cnt());
			            prst.setTimestamp(5, ComVO.getCom_e_dt());
			            prst.setString(6, ComVO.getCom_sts());
			            prst.setTimestamp(7, new Timestamp(new Date().getTime()));
			            prst.setString(8, ComVO.getCom_rmd());
			            prst.setBytes(9, ComVO.getCom_pic1());
			            prst.setBytes(10, ComVO.getCom_pic2());
			            prst.setBytes(11, ComVO.getIt_chk_pic1());
			            prst.setString(12, ComVO.getCom_id());
					    prst.executeUpdate();
					    c.setAutoCommit(true);
						c.commit();
						
					}

					
				} catch (SQLException e) {
					if(c!=null){
						try {
							c.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							
							e1.printStackTrace();
							throw new RuntimeException(e);
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
			public void updateCkPic(byte[] bytePic,String com_id){
				Connection c=null;
				try {
					c=ds.getConnection();
					c.setAutoCommit(false);
						PreparedStatement prst =c.prepareStatement("UPDATE COMMISION set IT_CHK_PIC1=? WHERE COM_ID=?");
						prst.setBytes(1,bytePic);
			            prst.setString(2, com_id);
					    prst.executeUpdate();
					    c.setAutoCommit(true);
						c.commit();
				} catch (Exception e) {
					
					if(c!=null){
						try {
							c.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							
							e1.printStackTrace();
							throw new RuntimeException(e);
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
			public List<ComVO> selectDaiGotoCount() throws RuntimeException{
				 Connection c=null;
				   List<ComVO> list2=new ArrayList<ComVO>();
					try {
					    c=ds.getConnection();		
						PreparedStatement prst =c.prepareStatement("select * from commision where com_sts='招募代購人' and  LT_M_DT  between sysdate-1/24 and sysdate+1/24");	
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
							TimeObject tob=new TimeObject();
							tob.setLeftTime(ComVO.getLmt_m_dt().getTime());
							ComVO.setTimeObject(tob);
							
							list2.add(ComVO);
						}
				}catch(SQLException e){
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally{
					if(c!=null){
						try {
							c.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				}
				return list2;
			}
			
			
			

			public List<ComVO> selectTuanGoToCount() {
				List<ComVO> list=new ArrayList();
				PreparedStatement prst=null;
				Connection c=null;
				try {
					c=ds.getConnection();
					prst =c.prepareStatement("Select * from COMMISION where com_sts='招募參與' and  COM_e_DT  between sysdate-1/24 and sysdate+1/24");
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
				    	TimeObject to=new TimeObject();
				    	to.setLeftTime(comvo.getCom_e_dt().getTime());

				    	comvo.setTimeObject(to);
				    	
				    	list.add(comvo);
					}
					
				} catch (SQLException e) {
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
}
