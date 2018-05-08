package com.comrf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComRfDAOJDBC implements ComRfDAOInterface{
	 static final String INSERT="INSERT INTO COMMISION_REFUND (COM_RF_ID,MEM_ID,EMP_NO,COM_ID,IT_NUM,IT_SUM,RF_CNT,RF_MT,RF_STS,RF_E_DATE,RF_E_END) VALUES('COM_RF'||LPAD(TO_CHAR(COM_RF_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?)";
	  static final String SELECT="SELECT * FROM COMMISION_REFUND WHERE COM_RF_ID=?";
	  static final String DELETE="DELETE FROM COMMISION_REFUND WHERE COM_RF_ID=?";
	  static final String UPDATE="UPDATE COMMISION_REFUND SET MEM_ID=?,EMP_NO=?,COM_ID=?,IT_NUM=?,IT_SUM=?,RF_CNT=?,RF_MT=?,RF_STS=?,Rf_date=?,RF_E_DATE=?,RF_E_END=? WHERE COM_RF_ID=?";
	  static final String SELECTALL="SELECT * FROM COMMISION_REFUND";
@Override
public ComRfVO selectByPrimaryKey(String comrfid) {
	  Connection c=null;
	  ComRfVO rf=new ComRfVO();
	try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
		    c.setAutoCommit(false);
		    PreparedStatement prst=c.prepareStatement(this.SELECT);
		    
		   prst.setString(1,comrfid);
		   ResultSet rs=prst.executeQuery();
		   while(rs.next()){
		   rf.setCom_rf_id(rs.getString(1));
		   rf.setMem_id(rs.getString(2));
		   rf.setEmp_no(rs.getString(3));
		   rf.setCom_id(rs.getString(4));
		   rf.setIt_num(rs.getInt(5));
		   rf.setIt_sum(rs.getInt(6));
		   rf.setRf_cnt(rs.getClob(7).toString());
		   rf.setRf_mt(rs.getString(8));
		   rf.setRf_sts(rs.getString(9));
		   rf.setRf_date(rs.getTimestamp(10));
		   rf.setRf_e_date(rs.getTimestamp(11));
		   rf.setRf_e_end(rs.getTimestamp(12));
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
	             return rf;
	}

@Override
public ComRfVO insert(ComRfVO crf) {
	Connection c=null;
	try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	   
		    c.setAutoCommit(false);
		    String[] generateKey=new String[]{"COM_RF_ID"};
		    PreparedStatement prst=c.prepareStatement(this.INSERT,generateKey);
		    prst.setString(1, crf.getMem_id());
		    prst.setString(2, crf.getEmp_no());
		    prst.setString(3, crf.getCom_id());
		    prst.setInt(4, crf.getIt_num());
		    prst.setInt(5, crf.getIt_sum());
		    prst.setString(6, crf.getRf_cnt());
		    prst.setString(7, crf.getRf_mt());
		    prst.setString(8, crf.getRf_sts());
		    prst.setTimestamp(9, crf.getRf_e_date());
		    prst.setTimestamp(10, crf.getRf_e_end());
		    prst.executeUpdate();
		    ResultSet rs=prst.getGeneratedKeys();
		    while(rs.next()){
		    	crf.setCom_rf_id(rs.getString(1));
		    }
		    c.setAutoCommit(true);
	        c.commit();
	        
     	    }catch(Exception e){
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			e.printStackTrace();
		}
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
	return crf;
}
@Override
public void delete(String comrfid) {
	        Connection c=null;

		    try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	   
				    c.setAutoCommit(false);
				    PreparedStatement prst=c.prepareStatement(this.DELETE);
				    prst.setString(1, comrfid);
				    prst.executeUpdate();
				    c.setAutoCommit(true);
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
public void update(ComRfVO comrfvo) {
	  Connection c=null;
	 
	    try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	   
			    c.setAutoCommit(false);
			    PreparedStatement prst=c.prepareStatement(this.UPDATE);
			    prst.setString(1, comrfvo.getMem_id());
			    prst.setString(2, comrfvo.getEmp_no());
			    prst.setString(3, comrfvo.getCom_id());
			    prst.setInt(4, comrfvo.getIt_num());
			    prst.setInt(5, comrfvo.getIt_sum());
			    prst.setString(6, comrfvo.getRf_cnt());
			    prst.setString(7, comrfvo.getRf_mt());
			    prst.setString(8, comrfvo.getRf_sts());
			    prst.setTimestamp(9, comrfvo.getRf_date());
			    prst.setTimestamp(10, comrfvo.getRf_e_date());
			    prst.setTimestamp(11, comrfvo.getRf_e_end());
			    prst.setString(12, comrfvo.getCom_rf_id());
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
public List<ComRfVO> selectAll() {
	  Connection c=null;
      List<ComRfVO> list=new ArrayList<ComRfVO>();
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	   
			    PreparedStatement prst=c.prepareStatement(this.SELECTALL);			  
			   ResultSet rs= prst.executeQuery();
			   while(rs.next()){
				   ComRfVO crf=new ComRfVO();
				   crf.setCom_rf_id(rs.getString(1));
				   crf.setMem_id(rs.getString(2));
				   crf.setEmp_no(rs.getString(3));
				   crf.setCom_id(rs.getString(4));
				   crf.setIt_num(rs.getInt(5));
				   crf.setIt_sum(rs.getInt(6));
				   crf.setRf_cnt(rs.getClob(7).toString());
				   crf.setRf_mt(rs.getString(8));
				   crf.setRf_sts(rs.getString(9));
				   crf.setRf_date(rs.getTimestamp(10));
				   crf.setRf_e_date(rs.getTimestamp(11));
				   crf.setRf_e_end(rs.getTimestamp(12));
			     list.add(crf);	   
			   }
			  
		 } catch (ClassNotFoundException | SQLException e) {		
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
