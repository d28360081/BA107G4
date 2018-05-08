package android.com.comt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class ComtDAO implements ComtDAO_interface{

	 static DataSource ds;
	 static {
			
			try {
				Context context=new javax.naming.InitialContext();
				ds=(DataSource) context.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		static final String INSERT="INSERT INTO COMMISION_COMMENT (COM_ID,COMT_ID,MEM_ID,CMT_CNT) VALUES(?,'COM_COMT'||LPAD(TO_CHAR(COMT_SEQ.NEXTVAL),3,'0'),?,?)";
		static final String DELETE="DELETE FROM COMMISION_COMMENT WHERE COM_ID=? AND COMT_ID=? ";	
		static final String UPDATE="UPDATE COMMISION_COMMENT SET MEM_ID=? , CMT_CNT=? WHERE COM_ID=? AND COMT_ID=?";
		static final String SELECT="SELECT * FROM COMMISION_COMMENT WHERE COM_ID=? AND COMT_ID=?";
		static final String SELECTALL="SELECT * FROM COMMISION_COMMENT";
		static final String SELECT_BY_COMID="SELECT * FROM COMMISION_COMMENT WHERE COM_ID=?";

		
	@Override
	public ComComtVO insert(ComComtVO comcomtvo) {
		 Connection c=null;
			try {
				c=ds.getConnection();
			c.setAutoCommit(false);
			String[] generateKey=new String[]{"COMT_ID"};
			PreparedStatement prst =c.prepareStatement(this.INSERT,generateKey);
			prst.setString(1,comcomtvo.getCom_id());
			prst.setString(2,comcomtvo.getMem_id());
			prst.setString(3,comcomtvo.getComt_cnt());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
			ResultSet rs=prst.getGeneratedKeys();
			while(rs.next()){
				comcomtvo.setComt_id(rs.getString(1));
			}
			
		} catch (SQLException e) {
			if(c!=null){
				try {
					System.out.println(1);
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
		return comcomtvo;
		
		
	}

	@Override
	public List selectAll(String comId) {
		List<ComComtVO> list=new ArrayList();
		 Connection c=null;
			try {
				c=ds.getConnection();
			    PreparedStatement prst=c.prepareStatement(SELECT_BY_COMID);
			    prst.setString(1,comId);

			    ResultSet rs=prst.executeQuery();
			    while(rs.next()){
			    	ComComtVO comcomtvo=new ComComtVO();
			    	comcomtvo.setCom_id(rs.getString(1));
			    	comcomtvo.setComt_id(rs.getString(2));
			    	comcomtvo.setMem_id(rs.getString(3));
			    	comcomtvo.setComt_cnt(rs.getString(4));
//			    	comcomtvo.setComt_art_pic1(rs.getBytes(5));
//			    	comcomtvo.setComt_art_pic2(rs.getBytes(6));
			    	list.add(comcomtvo);
			    }
			    	
	     	    }catch(Exception e){
				
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
		return list;
	}

}
