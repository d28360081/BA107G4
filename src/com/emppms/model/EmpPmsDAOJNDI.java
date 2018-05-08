package com.emppms.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emppmscls.model.EmpPmsClsVO;

public class EmpPmsDAOJNDI implements EmpPmsInterface{
	 static final String INSERT="INSERT INTO EMP_PERMISSION (EMP_NO,PMS_ID) VALUES (?,?)";
	  static final String SELECT="SELECT * FROM EMP_PERMISSION WHERE EMP_NO=?";
	  static final String DELETE="DELETE FROM EMP_PERMISSION WHERE EMP_NO=? and PMS_ID=?";
	  static final String UPDATE="UPDATE EMP_PERMISSION SET PMS_ID=? WHERE EMP_NO=? AND PMS_ID=?";
	  static final String SELECTALL="SELECT * FROM EMP_PERMISSION";
	  static final String DELETEALL="DELETE FROM EMP_PERMISSION WHERE EMP_NO=?";
	  static DataSource ds;
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
	public List<EmpPmsVO> selectByPrimaryKey(String empno) {
		List<EmpPmsVO> list=new ArrayList<EmpPmsVO>();
		Connection c=null;	 
		try {
			 c=ds.getConnection();	
			    PreparedStatement prst=c.prepareStatement(this.SELECT);
			   prst.setString(1,empno);
			   ResultSet rs=prst.executeQuery();
			   while(rs.next()){
				   EmpPmsVO eps=new EmpPmsVO();
				  eps.setEmpno(rs.getString(1));
				  eps.setPmsid(rs.getString(2));
				  list.add(eps);
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

	@Override
	public EmpPmsVO insert(EmpPmsVO eps) {
		Connection c=null;	 
		try {
			    c=ds.getConnection();	   
			    c.setAutoCommit(false);
			    String[] generateKey =new String[]{"EMP_NO"};
			    PreparedStatement prst=c.prepareStatement(this.INSERT,generateKey);
			    prst.setString(1, eps.getEmpno());
			    prst.setString(2, eps.getPmsid());
			    prst.executeUpdate();
			    ResultSet rs=prst.getGeneratedKeys();
			    
			    while(rs.next()){
			    	eps.setEmpno(rs.getString(1));
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
			}}finally{
				if(c!=null){
					try {
						c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	      }
		return eps;
	}
	@Override
	public void delete(String empno,String pms_Id) {
		Connection c=null;	 
		try {
			 c=ds.getConnection();	   
					    c.setAutoCommit(false);
					    PreparedStatement prst=c.prepareStatement(this.DELETE);
					    prst.setString(1, empno);
					    prst.setString(2, pms_Id);
					    prst.executeUpdate();
					    c.setAutoCommit(true);
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
	public List<EmpPmsVO> selectAll() {
	      List<EmpPmsVO> list=new ArrayList<EmpPmsVO>();
	      Connection c=null;	 
			try {
				 c=ds.getConnection();	   
				    PreparedStatement prst=c.prepareStatement(this.SELECTALL);			  
				   ResultSet rs= prst.executeQuery();
				   while(rs.next()){
					   EmpPmsVO crf=new EmpPmsVO();
					   crf.setEmpno(rs.getString(1));
					   crf.setPmsid(rs.getString(2));								 
				     list.add(crf);	   
				   }
				  
			 } catch (SQLException e) {		
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

	@Override
	public void update(EmpPmsVO emppmsvo, String pms_Id_new) {
		Connection c=null;	 
		try {
			 c=ds.getConnection();	
			    c.setAutoCommit(false);
			    PreparedStatement prst=c.prepareStatement(this.UPDATE);
			    prst.setString(1, pms_Id_new);
			    prst.setString(2, emppmsvo.getEmpno());
			    prst.setString(3, emppmsvo.getPmsid());
			    prst.executeUpdate();
			    c.setAutoCommit(true);
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
	public void deleteAll(String empno) {
	      Connection c=null;	 
			try {
				 c=ds.getConnection();	   
				   PreparedStatement prst=c.prepareStatement(this.DELETEALL);			
				   prst.setString(1, empno);
				   c.setAutoCommit(false);
				   prst.executeUpdate();
                   c.setAutoCommit(true);
                   c.commit();
			 } catch (SQLException e) {		
				e.printStackTrace();
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

	}
	
	
	
	
}
