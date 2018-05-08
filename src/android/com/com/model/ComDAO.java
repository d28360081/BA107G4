package android.com.com.model;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import android.com.compant.model.ComPantLsService;
import android.com.compant.model.ComPantLsVO;
import android.com.main.ImageUtil;
import android.com.member.model.MemberVO;


public class ComDAO implements ComDAO_interface{
	private static DataSource ds=null;
	static {
			  try {
			   Context ctx = new InitialContext();
			   ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			  } catch (NamingException e) {
			   e.printStackTrace();
			  }
	 }
	 	static final String FIND_GROUP_BUY_CASE = "SELECT * FROM COMMISION WHERE COM_STS LIKE '招募參與'";
	 	static final String FIND_SINGLE_CASE = "SELECT * FROM COMMISION WHERE COM_STS LIKE '招募代購人'";
	 	private String INSERT="insert into COMMISION (COM_ID ,MEM_ID , COM_TIT, COM_CNT,COM_S_DT, COM_E_DT, COM_STS, "
				+ "LT_M_DT, LMT_LCL , LMT_ATD_LV, LMT_AUC_LV,COM_MIN_NUM, COM_MX_NUM, LMT_M_PRC,LMT_DEL_PRC , PUR_S_DATE, "
				+ "PUR_E_DATE, COM_PIC1, COM_PIC2, IT_CHK_PIC1, COM_IT_STS, "
				+ "BNS_NUMBER, COM_PRC, IT_SZ, IT_COL,COM_RMD) values ('COM'||LPAD(TO_CHAR(COM_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		 final String UPDATE_TIT="UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=?,com_sts=?,LT_M_DT=?,COM_RMD=? WHERE COM_ID=?";
		 final String UPDATE="UPDATE COMMISION SET COM_TIT=?,LMT_ATD_LV=?,LMT_AUC_LV=?,COM_CNT=?,COM_E_DT=?,com_sts=?,LT_M_DT=?,COM_RMD=?,COM_PIC1=?,COM_PIC2=? WHERE COM_ID=?";
		 static final String selectJoinGroupFromComPantsLs ="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS='招募參與'";
		 static final String selectJoinGroupFromComLs ="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS<>'下架'";

		 final String selectJoinBuyFromComPantsLs="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS!='招募參與' AND COM_STS!='下架' AND COM_STS!='結案' AND COM_STS!='時間內未達成團購人數'";
		 final String SELECTHISTORY="SELECT * FROM COMMISION WHERE COM_ID=? AND COM_STS='結案'";
		 final String SELECTSELF="SELECT * FROM COMMISION WHERE MEM_ID=? and com_sts='招募參與'";
		 final String SELECT="SELECT * FROM COMMISION WHERE COM_ID=?";
		 final String SELECTEDIT="SELECT * FROM COMMISION WHERE MEM_ID=? AND COM_ID=?";
		 final String SELECT_BY_MEM_ID="SELECT * FROM COMMISION WHERE MEM_ID=? AND COM_STS<>'下架'";

	@Override
	public List<ComVO> selectWithAllComCase() {
		List<ComVO> comList = new ArrayList<>();
		ComVO comvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_SINGLE_CASE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comvo = new ComVO();
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
				comvo.setPur_s_date(rs.getTimestamp(17));
				//縮圖在這邊縮
				comvo.setCom_pic1(ImageUtil.shrink(rs.getBytes(18), 300));
				comvo.setCom_pic2(ImageUtil.shrink(rs.getBytes(19), 300));

				comvo.setCom_it_sts(rs.getString(21));
				comvo.setBns_number(rs.getInt(22));
				comvo.setCom_prc(rs.getInt(23));
				comvo.setIt_sz(rs.getString(24));
				comvo.setIt_col(rs.getString(25));
				comvo.setCom_rmd(rs.getString(26));
				

				comList.add(comvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return comList;
	}
	@Override
	public List<ComVO> selectWithAllGroupBuyCase() {
		List<ComVO> comList = new ArrayList<>();
		ComVO comvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_GROUP_BUY_CASE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comvo = new ComVO();
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
				comvo.setPur_s_date(rs.getTimestamp(17));
				//縮圖在這邊縮
				comvo.setCom_pic1(ImageUtil.shrink(rs.getBytes(18), 300));
				comvo.setCom_pic2(ImageUtil.shrink(rs.getBytes(19), 300));

				comvo.setCom_it_sts(rs.getString(21));
				comvo.setBns_number(rs.getInt(22));
				comvo.setCom_prc(rs.getInt(23));
				comvo.setIt_sz(rs.getString(24));
				comvo.setIt_col(rs.getString(25));
				comvo.setCom_rmd(rs.getString(26));
				

				comList.add(comvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return comList;
	}
	@Override
	public ComVO insert(ComVO comvo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String joinselect(String s, String s1) {
		return null;
	}
	@Override
	public ComVO update(ComVO comvo) {
		Connection c=null;
		try {
			c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE_TIT);
			
			prst.setString(1, comvo.getCom_tit());
            prst.setInt(2, comvo.getLmt_atd_clv());
            prst.setInt(3, comvo.getLmt_auc_lv());
            prst.setString(4, comvo.getCom_cnt());
            prst.setTimestamp(5, comvo.getCom_e_dt());
            prst.setString(6, comvo.getCom_sts());
            prst.setTimestamp(7, new Timestamp(new Date().getTime()));
            System.out.println("成功insert Timestamp"+new Timestamp(new Date().getTime()));
            
            prst.setString(8, comvo.getCom_rmd());
            prst.setString(9, comvo.getCom_id());
			System.out.println("執行結果"+prst.executeUpdate());
			
			c.setAutoCommit(true);
			c.commit();
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
		return comvo;
		
		
		
	}
	@Override
	public ComPantLsVO selectinsertcom(ComVO comvo,MemberVO memvo){
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
			compantlsvo.setCom_it_num(0);
			compantlsservice.insert(compantlsvo,c);
			
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
	
	@Override
	public List<ComVO> selectByMemID(String memid) {
		List<ComVO> comList = new ArrayList<>();
		ComVO comvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_MEM_ID);
			pstmt.setString(1, memid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comvo = new ComVO();
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
				comvo.setPur_s_date(rs.getTimestamp(17));
				//縮圖在這邊縮
				comvo.setCom_pic1(ImageUtil.shrink(rs.getBytes(18), 300));
				comvo.setCom_pic2(ImageUtil.shrink(rs.getBytes(19), 300));

				comvo.setCom_it_sts(rs.getString(21));
				comvo.setBns_number(rs.getInt(22));
				comvo.setCom_prc(rs.getInt(23));
				comvo.setIt_sz(rs.getString(24));
				comvo.setIt_col(rs.getString(25));
				comvo.setCom_rmd(rs.getString(26));
				

				comList.add(comvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return comList;
	}
	@Override
	public ComVO selectDelete(ComVO comvo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ComVO updatePic(ComVO comvo) {
		Connection c=null;
		try {
			c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE);
			
			prst.setString(1, comvo.getCom_tit());
            prst.setInt(2, comvo.getLmt_atd_clv());
            prst.setInt(3, comvo.getLmt_auc_lv());
            prst.setString(4, comvo.getCom_cnt());
            prst.setTimestamp(5, comvo.getCom_e_dt());
            prst.setString(6, comvo.getCom_sts());
            prst.setTimestamp(7, new Timestamp(new Date().getTime()));
            System.out.println("成功insert Timestamp"+new Timestamp(new Date().getTime()));
          

            prst.setString(8, comvo.getCom_rmd());
            ByteArrayInputStream bai1 =new ByteArrayInputStream(comvo.getCom_pic1());

            ByteArrayInputStream bai2 =new ByteArrayInputStream(comvo.getCom_pic2());
            prst.setBinaryStream(9, bai1);
            prst.setBinaryStream(10, bai2);
            
          
            prst.setString(11, comvo.getCom_id());
			System.out.println("執行結果"+prst.executeUpdate());
			
			c.setAutoCommit(true);
			c.commit();
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
		return comvo;
	}
	@Override
	public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list) {
		 Connection c=null;
		   List<ComVO> list2=new ArrayList<ComVO>();
			try {
			c=ds.getConnection();		
			for(ComPantLsVO cv:list){
				PreparedStatement prst =c.prepareStatement(selectJoinGroupFromComLs);	
				prst.setString(1,cv.getCom_id());
				System.out.println("指令"+cv.getCom_id());
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
					//縮圖在這邊縮
					ComVO.setCom_pic1(ImageUtil.shrink(rs.getBytes(18), 300));
					ComVO.setCom_pic2(ImageUtil.shrink(rs.getBytes(19), 300));
					//縮圖在這邊縮

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
		
		return null;
	}
	@Override
	public List<ComVO> selectHistory(String mem_id) {
		return null;
	}
	


}
