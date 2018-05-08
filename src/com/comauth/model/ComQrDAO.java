package com.comauth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.combid.model.ComBidDAOJdbc;
import com.combid.model.ComBidVO;

public class ComQrDAO implements ComQrInterface{
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
	public ComQrVO select(String com_id,String mem_id) throws RuntimeException{	
			 Connection c=null;
				try {
				c=ds.getConnection();
				ComQrVO comqrvo=new ComQrVO();
				PreparedStatement prst =c.prepareStatement("select * from comAuth where com_id=? and mem_id=?");
				prst.setString(1, com_id);		
				prst.setString(2, mem_id);
				ResultSet rs=prst.executeQuery();
				if(rs.next()) {
					comqrvo.setCom_id(rs.getString(1));
					comqrvo.setMem_id(rs.getString(2));
					comqrvo.setQr_code(rs.getBytes(3));
					comqrvo.setAuth_number(rs.getString(4));
					return comqrvo;
				}else{
					return null;
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				if(c!=null) {
					try {
						c.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
						throw new RuntimeException(e);
					
					}
				}
			}
			
		}

	

	@Override
	public void insert(ComQrVO comqrvo) throws RuntimeException{
		 Connection c=null;
			try {
			c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement("insert into comAuth (com_id,mem_id,QR_CODE,AUTH_NUMBER) values (?,?,?,?)");
			prst.setString(1, comqrvo.getCom_id());
		    prst.setString(2, comqrvo.getMem_id());
			prst.setBytes(3, comqrvo.getQr_code());
			prst.setString(4, comqrvo.getAuth_number());
		    prst.executeUpdate();
		    c.setAutoCommit(true);
		    c.commit();
		} catch (Exception e) {
			if(c!=null) {
				try {
					c.rollback();
				}  catch (Exception e2) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
		
	}

	@Override
	public void delete(ComQrVO comqrvo) throws RuntimeException{
		 Connection c=null;
			try {
			c=ds.getConnection();
			PreparedStatement prst =c.prepareStatement("DELETE from comAuth WHERE COM_ID=?");
			prst.setString(1, comqrvo.getCom_id());		
			prst.executeUpdate();
			
		} catch (Exception e) {
			if(c!=null) {
				try {
					c.rollback();
				}  catch (Exception e2) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				}  catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
	}

	@Override
	public void update(ComQrVO comqrvo) throws RuntimeException{
		 Connection c=null;
			try {
			c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement("UPDATE comAuth SET  qr_code=?, auth_number=? WHERE  COM_ID=? and mem_id=?");
			
			prst.setBytes(1,comqrvo.getQr_code() );
			prst.setString(2, comqrvo.getAuth_number());
			prst.setString(3, comqrvo.getCom_id());
	        prst.setString(4, comqrvo.getMem_id());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (Exception e) {
			if(c!=null) {
				try {
					c.rollback();
				}  catch (Exception e2) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
	}
  
}
