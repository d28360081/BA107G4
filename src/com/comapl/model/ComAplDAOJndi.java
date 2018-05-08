package com.comapl.model;

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

import com.combid.model.ComBidVO;

public class ComAplDAOJndi implements ComAplInterface{
	static final String INSERT="Insert into COMMISION_APPEAL (COM_APL_ID,MEM_ID,EMP_NO,COM_ID,APL_CNT,APL_STS) VALUES('COM_APL'||(LPAD(TO_CHAR(COM_APL_SEQ.NEXTVAL),3,'0')),?,?,?,?,?)";
	static final String SELECT="SELECT * FROM COMMISION_APPEAL WHERE COM_APL_ID=?";
	static final String UPDATE="UPDATE COMMISION_APPEAL SET MEM_ID=?, EMP_NO=?, COM_ID=?, APL_CNT=?, APL_STS=? WHERE COM_APL_ID=?";
	static final String DELETE="DELETE FROM COMMISION_APPEAL WHERE COM_APL_ID=?";
	static final String SELECTALL="SELECT * FROM COMMISION_APPEAL";
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
	public ComAplVO insert(ComAplVO comaplvo) {
		   Connection c=null;
			try {
				c=ds.getConnection();
			c.setAutoCommit(false);
			String[] generateKey=new String[]{"COM_APL_ID"};
			PreparedStatement prst =c.prepareStatement(INSERT,generateKey);
			prst.setString(1, comaplvo.getMem_id());
			prst.setString(2, comaplvo.getEmp_no());
			prst.setString(3, comaplvo.getCom_id());
			prst.setString(4,comaplvo.getApl_cnt());
			prst.setString(5, comaplvo.getApl_sts());
			prst.executeUpdate();
			ResultSet rs=prst.getGeneratedKeys();
			while(rs.next()){
				comaplvo.setCom_apl_id(rs.getString(1));
			}
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
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return comaplvo;
		}

		
	}

	@Override
	public void update(ComAplVO comaplvo) {
		   Connection c=null;
			try {
				c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst=c.prepareStatement(UPDATE);
		    prst.setString(1,comaplvo.getMem_id());
			prst.setString(2, comaplvo.getEmp_no());
			prst.setString(3,comaplvo.getCom_id());
			prst.setString(4, comaplvo.getApl_cnt());
			prst.setString(5, comaplvo.getApl_sts());
		    prst.setString(6, comaplvo.getCom_apl_id());
		    prst.executeUpdate();
		  
		    c.setAutoCommit(true);
		    c.commit();
		    
		} catch (SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
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
	public void delete(String comaltid) {
		   Connection c=null;
			try {
				c=ds.getConnection();
			c.setAutoCommit(false);
			PreparedStatement prst=c.prepareStatement(DELETE);
			prst.setString(1,comaltid);
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
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
	public ComAplVO select(String comaplid) {
		ComAplVO comaplvo=new ComAplVO();
		   Connection c=null;
			try {
				c=ds.getConnection();
			PreparedStatement prst=c.prepareStatement(SELECT);
			prst.setString(1, comaplid);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				comaplvo.setCom_apl_id(rs.getString(1));
				comaplvo.setMem_id(rs.getString(2));
				comaplvo.setEmp_no(rs.getString(3));
				comaplvo.setCom_id(rs.getString(4));
				comaplvo.setApl_cnt(rs.getString(5));
				comaplvo.setApl_sts(rs.getString(6));
				comaplvo.setCom_capl_date(rs.getTimestamp(7));
			}
			return comaplvo;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return comaplvo;
	}

	@Override
	public List<ComAplVO> selectAll() {
		List<ComAplVO> list=new ArrayList<ComAplVO>();
		   Connection c=null;
			try {
				c=ds.getConnection();
			PreparedStatement prst=c.prepareStatement(this.SELECTALL);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				ComAplVO comaplvo=new ComAplVO();
				comaplvo.setCom_id(rs.getString(1));
				comaplvo.setMem_id(rs.getString(2));
				comaplvo.setEmp_no(rs.getString(3));
				comaplvo.setCom_id(rs.getString(4));
				comaplvo.setApl_cnt(rs.getString(5));
				comaplvo.setApl_sts(rs.getString(6));
				comaplvo.setCom_capl_date(rs.getTimestamp(7));
				list.add(comaplvo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(c!=null) {
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
